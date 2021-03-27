package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class FindFirstPathHandler implements PathHandler {

    private final double target;

    private CPath min = new CPath(0);

    @Override
    public boolean handle(double... path) {
        double sum = Arrays.stream(path).sum();

        if (sum >= min.sum() && sum <= target) {
            min = new CPath(sum, path);

            if (sum == target) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void finish() {
        System.out.println(min);
    }
}
