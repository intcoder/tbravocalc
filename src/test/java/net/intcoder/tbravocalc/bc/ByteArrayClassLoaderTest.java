package net.intcoder.tbravocalc.bc;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ByteArrayClassLoaderTest {

    @Test
    void findClass() throws Exception {
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
        for (int i = 0; i < 5; i++) {
            var class0 = cl.loadClass("com.company.Main");
            var method = class0.getDeclaredMethod("test");
            int result = (int) method.invoke(null);

            assertEquals(1, result);
        }

        for (int i = 0; i < 5; i++) {
            var class0 = cl.findClass("com.company.Main");
            var method = class0.getDeclaredMethod("test");
            int result = (int) method.invoke(null);

            assertEquals(1, result);
        }
    }
}