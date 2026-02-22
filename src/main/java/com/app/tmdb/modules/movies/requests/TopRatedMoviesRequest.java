package com.app.tmdb.modules.movies.requests;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopRatedMoviesRequest extends PagedRequest {

    @Override
    protected void validate() {
    }
}
