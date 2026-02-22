package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchRequest extends PagedRequest {

    private String query;
    private Integer year;

    public static MovieSearchRequest of(String query, Integer year, String language, String region, Integer page) {
        MovieSearchRequest request = new MovieSearchRequest();
        request.setQuery(query);
        request.setYear(year);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {
        checkString(query, "query", false, 1, 200);
        checkInt(year, "year", true, 1900, 2100);
    }
}
