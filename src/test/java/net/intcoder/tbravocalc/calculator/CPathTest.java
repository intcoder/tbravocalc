package net.intcoder.tbravocalc.calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CPathTest {

    @Test
    void testToString() {
        var cPath = new CPath(10, 5, 5);
        assertEquals("5.0 + 5.0 = 10.0", cPath.toString());
    }
}