package net.intcoder.bc;

public class CompileException extends Exception {
    public CompileException() {
    }

    public CompileException(String message) {
        super(message);
    }

    public CompileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompileException(Throwable cause) {
        super(cause);
    }

    public CompileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
