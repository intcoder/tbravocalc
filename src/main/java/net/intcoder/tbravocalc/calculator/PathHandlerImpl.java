package net.intcoder.tbravocalc.calculator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class PathHandlerImpl implements PathHandler {


    private final double target;
    private final boolean printAll;

    private double min = 0;
    private long counter = 0;

    private static final int BUFFER_SIZE = 0;
    private final List<CPath> buffer = new ArrayList<>(BUFFER_SIZE);

    @Override
    public boolean handle(double... path) {
        double sum = Arrays.stream(path).sum();
        var cPath = new CPath(sum, path);

        if (sum >= min && sum <= target) {
            min = sum;
            counter++;

            buffer.add(cPath);

            if (sum == target) {
                if (!printAll) {
                    buffer.clear();
                    System.out.println(cPath);
                    return true;
                } else {
                    buffer.removeIf(cp -> cp.sum() != target);
                }
            }

            if (counter > BUFFER_SIZE) {
                flushBuffer();
            }
        }

        return false;
    }

    @Override
    public void finish() {
    }

    public void flushBuffer() {
        counter = 0;
        if (buffer.isEmpty()) return;

        var max = Collections.max(buffer);

        buffer.removeIf(cp -> cp.compareTo(max) != 0);
        buffer.forEach(System.out::println);

        buffer.clear();
    }
}
