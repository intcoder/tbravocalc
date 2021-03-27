package net.intcoder.bc;

import org.junit.jupiter.api.Test;

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
}