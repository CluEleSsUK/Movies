package com.patrickmcclurg.skymovies.service;

import com.patrickmcclurg.skymovies.exception.TechnicalFailureException;
import com.patrickmcclurg.skymovies.exception.TitleNotFoundException;

public interface ParentalControlService {

    boolean canWatchFilm(String filmId, String clientControlLevel)
            throws TitleNotFoundException, TechnicalFailureException;

}
