package com.patrickmcclurg.skymovies.exception;

public class TitleNotFoundException extends Exception {

    public TitleNotFoundException() {
        super("The movie service could not find the given movie");
    }
}
