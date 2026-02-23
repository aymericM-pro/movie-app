package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCreditsRequest extends PagedRequest {

    private Long movieId;

    public static MovieCreditsRequest of(long movieId, String language, String region, Integer page) {
        MovieCreditsRequest request = new MovieCreditsRequest();
        request.setMovieId(movieId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }
}
