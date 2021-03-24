package net.intcoder.tbravocalc.calculator;

import java.util.Arrays;

public class PathCheckerImpl implements PathChecker {

    private final double target;
    private double min = 0;

    public PathCheckerImpl(double target) {
        this.target = target;
    }

    @Override
    public boolean check(double... path) {
        double sum = Arrays.stream(path).sum();
        /*if (sum >= min && sum <= target)*/ {
            min = sum;

            Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
            System.out.print("\b\b\b");
            System.out.println(" = " + sum);

            if (sum == target) {
                return true;
            }
        }

        return false;
    }
}
