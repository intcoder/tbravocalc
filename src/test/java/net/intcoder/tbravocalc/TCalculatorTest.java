package net.intcoder.tbravocalc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TCalculatorTest {
    @Test
    @Disabled
    void main() throws Exception {
        TCalculator.main("-s", "/tmp/tmp.uW5uj05V0j/spreadsheet.txt",
                "-t", "3101",
                "-d", "17",
                "-r");;
    }
}