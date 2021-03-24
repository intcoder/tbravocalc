package net.intcoder.tbravocalc;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TCalculatorTest {
    @Test
    void main() throws Exception {
        TCalculator.main("-s", "/tmp/tmp.WElUMpWE7v/spreadsheet2.txt",
                "-t", "3101",
                "-d", "50",
                "-r"/*,
                "-l2"*//*,
                "-o", "/tmp/tmp.OY36MQaLQG",
                "-l2"*/);
    }
}