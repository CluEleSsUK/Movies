package com.patrickmcclurg.skymovies.service;

import com.patrickmcclurg.skymovies.exception.TechnicalFailureException;
import com.patrickmcclurg.skymovies.exception.TitleNotFoundException;
import com.patrickmcclurg.skymovies.model.Rating;

public class DefaultParentalControlService implements ParentalControlService {

    private MovieService movieService;

    public DefaultParentalControlService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public boolean canWatchFilm(String filmId, String clientControlLevel) throws TitleNotFoundException,
                                                                                 TechnicalFailureException {
        Rating filmRating = new Rating(movieService.getParentalControlLevel(filmId));
        Rating clientRating = new Rating(clientControlLevel);

        return clientRating.getRatingLevel() >= filmRating.getRatingLevel();
    }
}
