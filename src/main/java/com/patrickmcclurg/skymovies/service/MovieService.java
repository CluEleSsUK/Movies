package com.patrickmcclurg.skymovies.service;

import com.patrickmcclurg.skymovies.exception.TechnicalFailureException;
import com.patrickmcclurg.skymovies.exception.TitleNotFoundException;

public interface MovieService {
    String getParentalControlLevel(String movieId)
        throws TitleNotFoundException, TechnicalFailureException;
}
