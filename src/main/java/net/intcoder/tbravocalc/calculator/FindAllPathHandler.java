package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
public class FindAllPathHandler implements PathHandler {

    private final double target;

    private CPath min = new CPath(0);
    private final List<CPath> list = new LinkedList<>();

    @Override
    public boolean handle(double... path) {
        double sum = Arrays.stream(path).sum();

        if (sum >= min.sum() && sum <= target) {
            min = new CPath(sum, path);

            list.add(min);
        }

        return false;
    }

    @Override
    public void finish() {
        var maxOptional = list.stream().mapToDouble(CPath::sum).max();
        maxOptional.ifPresent(max -> {
            list.removeIf(cp -> cp.sum() != max);
            list.forEach(System.out::println);
        });
    }
}
