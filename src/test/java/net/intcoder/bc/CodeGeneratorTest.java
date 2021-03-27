package net.intcoder.bc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeGeneratorTest {

    @Test
    void generateSumN() {
        var cg = new CodeGenerator();
        assertEquals("n0", cg.generateSumN(1));
        assertEquals("n0+n1", cg.generateSumN(2));
        assertEquals("n0+n1+n2+n3+n4", cg.generateSumN(5));
    }

    @Test
    void generateCN() {
        var cg = new CodeGenerator();
        assertEquals("n0", cg.generateCN(1));
        assertEquals("n0,n1", cg.generateCN(2));
        assertEquals("n0,n1,n2,n3,n4", cg.generateCN(5));
    }

    @Test
    void generate() {
        var cg = new CodeGenerator();
        System.out.println(cg.generate(17));
    }
}