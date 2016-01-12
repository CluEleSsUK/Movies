package com.patrickmcclurg.skymovies;

import com.patrickmcclurg.skymovies.exception.TechnicalFailureException;
import com.patrickmcclurg.skymovies.exception.TitleNotFoundException;
import com.patrickmcclurg.skymovies.service.DefaultParentalControlService;
import com.patrickmcclurg.skymovies.service.MovieService;
import com.patrickmcclurg.skymovies.service.ParentalControlService;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultParentalControlServiceTest {

    private ParentalControlService parentalControlService;
    private MovieService movieService;

    private static final String VALID_FILM_ID = "123";
    private static final String INVALID_FILM_ID = "234";
    private static final String VALID_FILM_ID_WITH_TECHNICAL_ERROR = "345";

    @Before
    public void setup() throws Exception {
        movieService = mock(MovieService.class);
        parentalControlService = new DefaultParentalControlService(movieService);
        initMockBehaviour();
    }

    private void initMockBehaviour() throws Exception {
        when(movieService.getParentalControlLevel(VALID_FILM_ID)).thenReturn("12");
        when(movieService.getParentalControlLevel(INVALID_FILM_ID)).thenThrow(new TitleNotFoundException());
        when(movieService.getParentalControlLevel(VALID_FILM_ID_WITH_TECHNICAL_ERROR))
                .thenThrow(new TechnicalFailureException());
    }

    @Test
    public void shouldAllowViewingIfFilmEqualLevel() throws Exception {
        assertThat(parentalControlService.canWatchFilm(VALID_FILM_ID, "12"), is(true));
    }

    @Test
    public void shouldAllowViewingIfFilmLowerLevel() throws Exception {
        assertThat(parentalControlService.canWatchFilm(VALID_FILM_ID, "18"), is(true));

    }

    @Test
    public void shouldNotAllowViewingOfHigherLevelFilm() throws Exception {
        assertThat(parentalControlService.canWatchFilm(VALID_FILM_ID, "U"), is(false));
    }

    @Test
    public void shouldNotAllowViewingForInvalidLevel() throws Exception {
        assertThat(parentalControlService.canWatchFilm(VALID_FILM_ID, "an invalid level"), is(false));
    }

    @Test(expected = TitleNotFoundException.class)
    public void shouldPropagateTitleExceptionsFromMovieService() throws Exception {
        parentalControlService.canWatchFilm(INVALID_FILM_ID, "12");
    }

    @Test(expected = TechnicalFailureException.class)
    public void shouldPropagateTechnicalExceptionsFromMovieService() throws Exception {
        parentalControlService.canWatchFilm(VALID_FILM_ID_WITH_TECHNICAL_ERROR, "12");
    }

}
