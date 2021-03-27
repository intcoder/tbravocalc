package net.intcoder.tbravocalc;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
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
        OptionSpec<String> srcOption = optionsParser.acceptsAll(Arrays.asList("spreadsheet", "src", "s")).withRequiredArg().ofType(String.class);
        OptionSpec<Double> targetOption = optionsParser.acceptsAll(Arrays.asList("target", "t")).withRequiredArg().ofType(Double.class);
        OptionSpec<Integer> depthOption = optionsParser.acceptsAll(Arrays.asList("depth", "d")).withRequiredArg().ofType(Integer.class);
        OptionSpec<Void> reversedOption = optionsParser.acceptsAll(Arrays.asList("reversed", "r"));
        OptionSpec<Void> printAllOption = optionsParser.acceptsAll(Arrays.asList("all", "a"));
        //OptionSpec<String> outputFileOption = optionsParser.acceptsAll(Arrays.asList("output", "out", "o")).withRequiredArg().ofType(String.class);
        //OptionSpec<Integer> logLevelOption = optionsParser.acceptsAll(Arrays.asList("log", "l")).withRequiredArg().ofType(Integer.class).defaultsTo(1);

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
        //String outputFile = optionSet.valueOf(outputFileOption);
        //int logLevel = optionSet.valueOf(logLevelOption);


        //Path outputPath = outputFile != null ? Path.of(outputFile) : null;
        //boolean log = logLevel > 0;
        //boolean debug = logLevel > 1;
        //boolean trace = logLevel > 2;

        if (reversed) ArrayUtils.reverse(spreadsheet);

        System.out.println("Target: " + target);
        System.out.println("Spreadsheet: " + StringUtils.join(ArrayUtils.toObject(spreadsheet), "|"));
        System.out.println("Depth: " + depth);
        System.out.println("Reversed: " + reversed);
        System.out.println("Print all paths: " + printAll);

        var cg = new CodeGenerator();
        var srcCode = cg.generate(depth);

        //var handler = new FindFirstPathHandler(target);
        var handler = new FindAllPathHandler(target);

        Class<PathGenerator> c = CodeCompiler.compile("net.intcoder.tbravocalc.calculator.PathGeneratorImpl", srcCode);
        var pathGenerator = c.getDeclaredConstructor(PathHandler.class).newInstance(handler);

        pathGenerator.start(spreadsheet);
    }

    public static void printUsage() {
        System.out.println("ERROR!");
    }

    protected static double[] parseSpreadSheet(String filePath) throws IOException {
        var path = Path.of(filePath);
        return Files.readAllLines(path).stream().mapToDouble(Double::valueOf).toArray();
    }
}
