package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MoviesByGenreRequest extends PagedRequest {

    private Integer genreId;

    public static MoviesByGenreRequest of(Integer genreId, String language, String region, Integer page) {
        MoviesByGenreRequest request = new MoviesByGenreRequest();
        request.setGenreId(genreId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);
        checkInt(v, genreId, "genre", false, null, null);
    }
}
