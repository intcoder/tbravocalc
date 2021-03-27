package net.intcoder.tbravocalc.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class PathHandlerImpl implements PathHandler {

    @Getter
    protected final double target;
}
