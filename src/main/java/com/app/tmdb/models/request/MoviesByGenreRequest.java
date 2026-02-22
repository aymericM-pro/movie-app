package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviesByGenreRequest extends PagedRequest {

    private Integer genreId;

    @Override
    protected void validate() {
        if (genreId == null) {
            throw new IllegalArgumentException("genre is required");
        }
    }
}
