package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRequest extends PagedRequest {
    private Long movieId;

    public static MovieDetailsRequest of(Long movieId, String language, String region, Integer page) {
        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(movieId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {
        super.validate();
        checkLong(movieId, "movieId", false, true);
    }
}

