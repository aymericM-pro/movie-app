package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieCreditsRequest extends PagedRequest {

    private Long movieId;

    @Override
    protected void validate() {

    }
}
