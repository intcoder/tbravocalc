package net.intcoder.tbravocalc.calculator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public abstract class PathHandlerImpl implements PathHandler {

    @Getter
    protected final double target;
}
