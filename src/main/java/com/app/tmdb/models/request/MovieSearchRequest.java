package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchRequest extends PagedRequest {

    private String query;
    private Integer year;

    @Override
    protected void validate() {
        checkString(query, "query", false, 1, 200);
        checkInt(year, "year", true, 1900, 2100);
    }
}
