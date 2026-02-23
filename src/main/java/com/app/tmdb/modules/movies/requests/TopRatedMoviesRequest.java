package com.app.tmdb.modules.movies.requests;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopRatedMoviesRequest extends PagedRequest {

    public static TopRatedMoviesRequest of(String language, String region, Integer page) {
        TopRatedMoviesRequest r = new TopRatedMoviesRequest();
        r.setLanguage(language);
        r.setRegion(region);
        r.setPage(page);
        return r;
    }
}
