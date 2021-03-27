package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class VerbosePathHandler implements PathHandler {

    private final double target;

    private CPath min = new CPath(0);

    @Override
    public boolean handle(double... path) {
        double sum = Arrays.stream(path).sum();

        if (sum >= min.sum() && sum <= target) {
            min = new CPath(sum, path);

            System.out.println(min);
        }

        return false;
    }

    @Override
    public void finish() {
    }
}
