package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieListsRequest extends PagedRequest {
    private Long movieId;

    public static MovieListsRequest of(Long movieId, String language, String region, Integer page) {
        MovieListsRequest request = new MovieListsRequest();
        request.setMovieId(movieId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);
        checkLong(v, movieId, "movieId", false, true);
    }
}