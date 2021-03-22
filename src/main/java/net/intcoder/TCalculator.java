package net.intcoder;

import net.intcoder.bc.CodeGenerator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.file.PathUtils;
import org.apache.commons.lang3.ArrayUtils;

import javax.tools.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TCalculator {
    public static void main(String... args) throws Exception {
        double target = 3101;
        double[] spreadsheet = new double[]{
                780,
                184,
                688,
                280,
                414,
                3135,
                1499,
                654,
                652,
                1000,
                2791,
                120,
                120,
                120,
                358,
                120,
                99,
                198,
                50,
                120,
                120,
                150,
                265,
                290,
                83,
                279,
                250,
                170,
                210,
                746,
                5330,
                //99.98
        };
        int depth = 17;

        ArrayUtils.reverse(spreadsheet);

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
            var entry = new ZipEntry("net/intcoder/bc/Calculator.class");

            zipOut.putNextEntry(entry);
            in.transferTo(zipOut);

            zipOut.closeEntry();
            zipOut.close();
        }

        System.out.println("Compiled jar: " + compiledJar);

        FileUtils.deleteQuietly(tmpDir.toFile());


        var cl = new URLClassLoader(new URL[]{compiledJar.toUri().toURL()});

        Class<?> c = cl.loadClass("net.intcoder.bc.Calculator");
        var method = c.getDeclaredMethod("calculate", double[].class, Double.TYPE);
        method.invoke(null, spreadsheet, target);

        FileUtils.deleteQuietly(compiledJar.toFile());
    }
}
