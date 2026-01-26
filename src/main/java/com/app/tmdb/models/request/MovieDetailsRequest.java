package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieDetailsRequest extends ServiceParams {

    private Long movieId;
    private String language = "en-US";

    @Override
    protected void validate() {
        checkLong(movieId, "movieId", false, true);
        checkString(language, "language", false, 2, 10);
    }
}
