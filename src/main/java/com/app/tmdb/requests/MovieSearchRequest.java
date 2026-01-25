package com.app.tmdb.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchRequest extends ServiceParams {

    private String query;
    private Integer page = 1;
    private String language = "en-US";
    private Integer year;

    @Override
    protected void validate() {
        checkString(query, "query", false, 1, 200);
        checkInt(page, "page", true, 1, 500);
        checkString(language, "language", false, 2, 10);
        checkInt(year, "year", true, 1900, 2100);
    }
}
