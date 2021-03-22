package net.intcoder.tbravocalc;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import net.intcoder.tbravocalc.bc.CodeGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.tools.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TCalculator {
    public static void main(String... args) throws Exception {

        OptionParser optionsParser = new OptionParser();
        OptionSpec<String> srcOption = optionsParser.acceptsAll(Arrays.asList("spreadsheet", "src", "s")).withRequiredArg().ofType(String.class);
        OptionSpec<Double> targetOption = optionsParser.acceptsAll(Arrays.asList("target", "t")).withRequiredArg().ofType(Double.class);
        OptionSpec<Integer> depthOption = optionsParser.acceptsAll(Arrays.asList("depth", "d")).withRequiredArg().ofType(Integer.class);
        OptionSpec<Void> reversedOption = optionsParser.acceptsAll(Arrays.asList("reversed", "r"));

        OptionSet optionSet = optionsParser.parse(args);

        if (!optionSet.has(srcOption) || !optionSet.has(targetOption)) {
            printUsage();
            return;
        }

        double target = optionSet.valueOf(targetOption);
        double[] spreadsheet = parseSpreadSheet(optionSet.valueOf(srcOption));
        int depth = optionSet.has(depthOption) ? optionSet.valueOf(depthOption) : spreadsheet.length;
        boolean reversed = optionSet.has(reversedOption);

        if (reversed) ArrayUtils.reverse(spreadsheet);

        var cg = new CodeGenerator();
        var srcCode = cg.generate(depth);
        Path tmpDir = Files.createTempDirectory("tbravocalc");
        Path srcCodeFile = tmpDir.resolve("Calculator.java");
        Files.writeString(srcCodeFile, srcCode);

        System.out.println(srcCodeFile);


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> compilationUnits = fileManager
                .getJavaFileObjectsFromStrings(Arrays.asList(srcCodeFile.toString()));
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null,
                null, compilationUnits);
        boolean success = task.call();
        fileManager.close();
        System.out.println("Success: " + success);

        Path compiled = tmpDir.resolve("Calculator.class");
        Path compiledJar = Files.createTempFile(null, null);

        try (var in = Files.newInputStream(compiled);
             var out = Files.newOutputStream(compiledJar)) {
            var zipOut = new ZipOutputStream(out);
            var entry = new ZipEntry("net/intcoder/tbravocalc/bc/Calculator.class");

            zipOut.putNextEntry(entry);
            in.transferTo(zipOut);

            zipOut.closeEntry();
            zipOut.close();
        }

        System.out.println("Compiled jar: " + compiledJar);

        FileUtils.deleteQuietly(tmpDir.toFile());


        var cl = new URLClassLoader(new URL[]{compiledJar.toUri().toURL()});

        Class<?> c = cl.loadClass("net.intcoder.tbravocalc.bc.Calculator");
        var method = c.getDeclaredMethod("calculate", double[].class, Double.TYPE);
        method.invoke(null, spreadsheet, target);

        FileUtils.deleteQuietly(compiledJar.toFile());
    }

    public static void printUsage() {
        System.out.println("ERROR!");
    }

    protected static double[] parseSpreadSheet(String filePath) throws IOException {
        var path = Path.of(filePath);
        return Files.readAllLines(path).stream().mapToDouble(Double::valueOf).toArray();
    }
}
