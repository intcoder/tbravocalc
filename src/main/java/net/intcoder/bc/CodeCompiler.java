package net.intcoder.bc;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import javax.tools.*;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CodeCompiler {
    public static <T> Class<T> compile(String fullClassName, String src) {
        var compiled = compileToByteArray(fullClassName, src);
        var cl = new ByteArrayClassLoader(Map.of(fullClassName, compiled));
        try {
            return (Class<T>) cl.loadClass(fullClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Compilation error", e);
        }
    }

    public static byte[] compileToByteArray(String fullClassName, String src) {
        try {
            var packageName = StringUtils.substringBeforeLast(fullClassName, ".");
            var className = StringUtils.substringAfterLast(fullClassName, ".");

            var tmpDir = Files.createTempDirectory("codecompiler");
            var srcCodeFile = tmpDir.resolve(className + ".java");

            Files.writeString(srcCodeFile, src);

            var output = new ByteArrayOutputStream();
            var outputWriter = new OutputStreamWriter(output);

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromStrings(Arrays.asList(srcCodeFile.toString()));
            JavaCompiler.CompilationTask task = compiler.getTask(outputWriter, fileManager, diagnostics, List.of("--enable-preview", "--release", "15"), null, compilationUnits);
            boolean success = task.call();
            fileManager.close();

            if (!success) {
                var sb = new StringBuilder();
                //sb.append(output.toString()).append("\n");
                diagnostics.getDiagnostics().stream().map(Diagnostic::toString).forEach(sb::append);

                throw new CompileException(sb.toString());
            }

            var compiledFile = tmpDir.resolve(className + ".class");

            byte[] data = Files.readAllBytes(compiledFile);
            FileUtils.deleteQuietly(tmpDir.toFile());
            return data;
        } catch (Exception e) {
            throw new RuntimeException("Compilation error", e);
        }
    }
}
