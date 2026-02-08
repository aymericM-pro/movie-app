package com.app.tmdb.modules.shows.requests;

import com.app.tmdb.models.request.ServiceParams;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TvDetailsRequest extends ServiceParams {
    private Long tvId;
    private String language;

    @Override
    public void validate() {

    }
}