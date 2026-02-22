package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRequest extends PagedRequest {
    private Long movieId;

    public static MovieDetailsRequest of(Long movieId, String language) {
        MovieDetailsRequest r = new MovieDetailsRequest();
        r.setMovieId(movieId);
        r.setLanguage(language);
        return r;
    }

    @Override
    protected void validate() {
        super.validate();
        checkLong(movieId, "movieId", false, true);
    }
}

