package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

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
    protected void validate() {
        if (genreId == null) {
            throw new IllegalArgumentException("genre is required");
        }
    }
}
