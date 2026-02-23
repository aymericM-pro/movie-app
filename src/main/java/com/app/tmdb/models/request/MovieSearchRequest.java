package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);
        checkString(v, query, "query", false, 1, 200);
        checkInt(v, year, "year", true, 1900, 2100);
    }
}
