package net.intcoder.tbravocalc;

import java.util.Arrays;

public class PathPrinterImpl implements PathPrinter {

    private final boolean debug;
    private final boolean trace;


    public PathPrinterImpl(boolean debug, boolean trace) {
        this.debug = debug;
        this.trace = trace;
    }

    @Override
    public void printPath(double... path) {
        Arrays.stream(path).boxed().map(n -> n + " + ").forEach(System.out::print);
        System.out.print("\b\b\b");
        System.out.println(" = " + Arrays.stream(path).sum());
    }

    @Override
    public void debugPath(double... path) {
        if (!debug) return;
        System.out.print("\tDEBUG: ");
        printPath(path);
    }

    @Override
    public void tracePath(double... path) {
        if (!trace) return;
        System.out.print("\tTRACE: ");
        printPath(path);
    }
}
