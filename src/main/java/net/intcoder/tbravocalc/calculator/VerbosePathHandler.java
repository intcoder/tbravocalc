package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

public class VerbosePathHandler extends PathHandlerImpl {

    private CPath min = new CPath(0);

    public VerbosePathHandler(double target) {
        super(target);
    }

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
