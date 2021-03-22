package net.intcoder.bc;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculate() {

        double target = 3101;
        double[] spreadsheet = new double[] {
                780,
                184,
                688,
                280,
                414,
                3135,
                1499,
                654,
                652,
                1000,
                2791,
                120,
                120,
                120,
                358,
                120,
                99,
                198,
                50,
                120,
                120,
                150,
                265,
                290,
                83,
                279,
                250,
                170,
                210,
                746,
                5330,
                //99.98
        };

        ArrayUtils.reverse(spreadsheet);

        Calculator.calculate(spreadsheet, target);
    }
}