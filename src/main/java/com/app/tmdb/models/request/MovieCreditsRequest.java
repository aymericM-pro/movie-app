package com.app.tmdb.models.request;

import lombok.Data;

@Data
public class MovieCreditsRequest extends ServiceParams {

    private Long movieId;
    private String language;

    @Override
    protected void validate() {

    }
}
