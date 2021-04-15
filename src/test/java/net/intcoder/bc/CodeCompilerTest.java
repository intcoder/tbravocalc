package net.intcoder.bc;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CodeCompilerTest {

    @Test
    void compile() throws Exception {
        var code = """
                package com.company;
                                
                public class Main {
                                
                    public static int test() throws Exception {
                        return 1;
                    }
                }
                                
                """;

        var class0 = CodeCompiler.compile("com.company.Main", code);
        var method = class0.getDeclaredMethod("test");
        int result = (int) method.invoke(null);

        assertEquals(1, result);
    }

    @Test
    void compileToByteArray() throws Exception {
        var code = """
                package com.company;
                                
                public class Main {
                                
                    public static int test() throws Exception {
                        return 1;
                    }
                }
                                
                """;

        var data = CodeCompiler.compileToByteArray("com.company.Main", code);
        var cl = new ByteArrayClassLoader(Map.of("com.company.Main", data));

        var class0 = cl.loadClass("com.company.Main");
        var method = class0.getDeclaredMethod("test");
        int result = (int) method.invoke(null);

        assertEquals(1, result);
    }


    /////////////////


    @Test
    @SneakyThrows
    void name() {
        Path root = Path.of("/tmp/tmp.ATVsRBUzTn/");
        System.out.println(root.toAbsolutePath().toString());

        var cg = new CodeGenerator();

        for (int i = 0; i < 10; i++) {
            var fileName = "compiled" + i + ".class";
            Path file = root.resolve(fileName);

            var srcCode = cg.generate(i);
            byte[] compiled = CodeCompiler.compileToByteArray("net.intcoder.tbravocalc.calculator.PathGeneratorImpl", srcCode);

            Files.write(file, compiled);
        }
    }
}