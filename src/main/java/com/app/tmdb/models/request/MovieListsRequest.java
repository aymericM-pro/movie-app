package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieListsRequest extends PagedRequest {
    private Long movieId;

    @Override
    protected void validate() {
        checkLong(movieId, "movieId", false, true);
    }
}