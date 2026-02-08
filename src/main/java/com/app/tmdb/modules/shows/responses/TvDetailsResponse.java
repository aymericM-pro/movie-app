package com.app.tmdb.modules.shows.responses;

import lombok.Data;

@Data
public class TvDetailsResponse {
    private TvMainDetails details;
    private  TvCreditsResponse credits;
}
