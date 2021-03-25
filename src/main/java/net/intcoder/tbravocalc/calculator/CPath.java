package net.intcoder.tbravocalc.calculator;

import java.util.Arrays;

public record CPath(double sum, double... path) implements Comparable<CPath> {
    @Override
    public String toString() {
        var sb = new StringBuilder();
        Arrays.stream(path).boxed().map(n -> n + " + ").forEach(sb::append);
        sb.delete(sb.length()-3, sb.length());
        sb.append(" = ").append(sum);

        return sb.toString();
    }

    @Override
    public int compareTo(CPath o) {
        return Double.compare(this.sum(), o.sum());
    }
}
