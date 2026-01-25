package com.app.tmdb.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieListsRequest extends ServiceParams {

    private Long movieId;
    private Integer page = 1;
    private String language = "en-US";

    @Override
    protected void validate() {
        checkLong(movieId, "movieId", false, true);
        checkInt(page, "page", true, 1, 500);
        checkString(language, "language", false, 2, 10);
    }
}
