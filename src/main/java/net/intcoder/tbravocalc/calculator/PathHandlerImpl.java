package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public class PathHandlerImpl implements PathHandler {

    private final double target;
    private final boolean printAll;

    private double min = 0;

    @Override
    public boolean handle(double... path) {
        double sum = Arrays.stream(path).sum();
        if (sum >= min && sum <= target) {
            min = sum;

            Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
            System.out.print("\b\b\b");
            System.out.println(" = " + sum);

            if (sum == target && !printAll) {
                return true;
            }
        }

        return false;
    }
}
