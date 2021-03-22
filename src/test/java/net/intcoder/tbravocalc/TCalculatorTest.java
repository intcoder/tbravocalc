package net.intcoder.tbravocalc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TCalculatorTest {
    @Test
    void main() throws Exception {
        TCalculator.main("-s", "/tmp/tmp.hw8egPq8oc/spreadsheet.txt",
                "-t", "3101",
                "-d", "17",
                "-r");;
    }
}