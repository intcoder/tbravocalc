package net.intcoder.tbravocalc;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;

class TCalculatorTest {

    @Test
    void main() throws Exception {
        var tmp = Files.createTempFile("test-spreadsheet.txt", "tmp");

        try (var in = TCalculatorTest.class.getResourceAsStream("/spreadsheet.txt");
             var out = Files.newOutputStream(tmp)) {
            in.transferTo(out);
        }

        TCalculator.main("-s", tmp.toAbsolutePath().toString(),
                                "-t4524");

        Files.delete(tmp);
    }
}