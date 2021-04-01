package net.intcoder.tbravocalc;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.intcoder.bc.CodeCompiler;
import net.intcoder.bc.CodeGenerator;
import net.intcoder.tbravocalc.calculator.*;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class TCalculator {
    public static void main(String... args) throws Exception {

        OptionParser optionsParser = new OptionParser();
        var srcOption = optionsParser.acceptsAll(Arrays.asList("spreadsheet", "src", "s")).withRequiredArg().ofType(String.class);
        var targetOption = optionsParser.acceptsAll(Arrays.asList("target", "t")).withRequiredArg().ofType(Double.class);
        var depthOption = optionsParser.acceptsAll(Arrays.asList("depth", "d")).withRequiredArg().ofType(Integer.class);
        var reversedOption = optionsParser.acceptsAll(Arrays.asList("reversed", "r"));
        var printAllOption = optionsParser.acceptsAll(Arrays.asList("all", "a"));
        var verboseOption = optionsParser.acceptsAll(Arrays.asList("verbose", "v"));

        OptionSet optionSet = optionsParser.parse(args);

        if (!optionSet.has(srcOption) || !optionSet.has(targetOption)) {
            printUsage();
            return;
        }

        double target = optionSet.valueOf(targetOption);

        double[] spreadsheet = Arrays.stream(parseSpreadSheet(optionSet.valueOf(srcOption))).filter(n -> n <= target).toArray();
        int depth = optionSet.has(depthOption) ? optionSet.valueOf(depthOption) : spreadsheet.length;
        boolean reversed = optionSet.has(reversedOption);
        boolean printAll = optionSet.has(printAllOption);
        boolean verbose = optionSet.has(verboseOption);

        PrintType printType = PrintType.parse(printAll, verbose);

        if (reversed) ArrayUtils.reverse(spreadsheet);

        System.out.println("Target: " + target);
        System.out.println("Spreadsheet: " + StringUtils.join(ArrayUtils.toObject(spreadsheet), "|"));
        System.out.println("Depth: " + depth);
        System.out.println("Reversed: " + reversed);
        System.out.println("Print: " + printType.toString());

        var cg = new CodeGenerator();
        var srcCode = cg.generate(depth);

        var handler = switch (printType) {
            case FIRST -> new FindFirstPathHandler(target);
            case ALL -> new FindAllPathHandler(target);
            case VERBOSE -> new VerbosePathHandler(target);
        };

        Class<PathGenerator> c = CodeCompiler.compile("net.intcoder.tbravocalc.calculator.PathGeneratorImpl", srcCode);
        var pathGenerator = c.getDeclaredConstructor(PathHandler.class).newInstance(handler);

        System.out.println("\nPaths: ");
        long startTime = System.currentTimeMillis();
        pathGenerator.start(spreadsheet);

        long calculationTime = System.currentTimeMillis() - startTime;
        System.out.println("\nCalculation time:");
        System.out.println(calculationTime + " ms");
        System.out.println(calculationTime/1000 + " s");
        System.out.println(calculationTime/1000/60 + " m");
    }

    public static void printUsage() {
        System.out.println("ERROR!");
    }

    protected static double[] parseSpreadSheet(String filePath) throws IOException {
        var path = Path.of(filePath);
        return Files.readAllLines(path).stream().mapToDouble(Double::valueOf).toArray();
    }

    enum PrintType {
        FIRST,
        ALL,
        VERBOSE;

        public static PrintType parse(boolean printAll, boolean verbose) {
            return verbose ? VERBOSE : printAll ? ALL : FIRST;
        }
    }
}
