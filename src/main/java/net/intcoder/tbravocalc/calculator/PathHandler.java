package net.intcoder.tbravocalc.calculator;

public interface PathHandler {
    boolean handle(double... path);
    void finish();
}
