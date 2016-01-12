package com.patrickmcclurg.skymovies.exception;

public class TechnicalFailureException extends Exception {

    public TechnicalFailureException() {
        super("System error");
    }
}
