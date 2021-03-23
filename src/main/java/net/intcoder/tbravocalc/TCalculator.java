package net.intcoder.tbravocalc;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.intcoder.tbravocalc.bc.CodeCompiler;
import net.intcoder.tbravocalc.bc.CodeGenerator;
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
        OptionSpec<String> outputFileOption = optionsParser.acceptsAll(Arrays.asList("output", "out", "o")).withRequiredArg().ofType(String.class);
        OptionSpec<Integer> logLevelOption = optionsParser.acceptsAll(Arrays.asList("log", "l")).withRequiredArg().ofType(Integer.class).defaultsTo(1);

        OptionSet optionSet = optionsParser.parse(args);

        if (!optionSet.has(srcOption) || !optionSet.has(targetOption)) {
            printUsage();
            return;
        }

        double target = optionSet.valueOf(targetOption);
        double[] spreadsheet = parseSpreadSheet(optionSet.valueOf(srcOption));
        int depth = optionSet.has(depthOption) ? optionSet.valueOf(depthOption) : spreadsheet.length;
        boolean reversed = optionSet.has(reversedOption);
        boolean printAll = optionSet.has(printAllOption);
        String outputFile = optionSet.valueOf(outputFileOption);
        int logLevel = optionSet.valueOf(logLevelOption);


        Path outputPath = outputFile != null ? Path.of(outputFile) : null;
        boolean log = logLevel > 0;
        boolean debug = logLevel > 1;
        boolean trace = logLevel > 2;

        if (reversed) ArrayUtils.reverse(spreadsheet);

        System.out.println("Target: " + target);
        System.out.println("Spreadsheet: " + StringUtils.join(ArrayUtils.toObject(spreadsheet), "|"));
        System.out.println("Depth: " + depth);
        System.out.println("Reversed: " + reversed);
        System.out.println("Print all paths: " + printAll);

        var cg = new CodeGenerator(printAll);
        var srcCode = cg.generate(depth);

        Class<?> c = CodeCompiler.compile("net.intcoder.tbravocalc.bc.Calculator", srcCode);

        var outWriter = outputPath != null ? Files.newBufferedWriter(outputPath) : null;

        var instance = c.getDeclaredConstructor(PathPrinter.class).newInstance(new PathPrinter() {

            private final boolean writeToFile = outWriter != null;

            @Override
            public void printPath(double... path) {
                print("", true, log, path);
            }

            @Override
            public void debugPath(double... path) {
                print("\tDEBUG: ", false, debug, path);
            }

            @Override
            public void tracePath(double... path) {
                print("\tTRACE: ", false, trace, path);
            }

            private void print(String prefix, boolean print, boolean write, double... path) {
                var sb = new StringBuilder();
                sb.append(prefix);
                Arrays.stream(path).boxed().map(n -> n + " + ").forEach(sb::append);
                sb.delete(sb.length()-3, sb.length());
                sb.append(" = ").append(Arrays.stream(path).sum());

                var s = sb.toString();

                if (print) System.out.println(s);

                if (writeToFile && write) {
                    try {
                        outWriter.write(s + "\n");
                        outWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        var method = c.getDeclaredMethod("calculate", double[].class, Double.TYPE);
        method.invoke(instance, spreadsheet, target);

        if (outWriter != null) outWriter.close();
    }

    public static void printUsage() {
        System.out.println("ERROR!");
    }

    protected static double[] parseSpreadSheet(String filePath) throws IOException {
        var path = Path.of(filePath);
        return Files.readAllLines(path).stream().mapToDouble(Double::valueOf).toArray();
    }
}
