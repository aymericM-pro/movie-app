package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvFullResponse {
    private TvMainDetails details;
    private TvCreditsResponse credits;
    private TvVideosResponse videos;
    private TvImagesResponse images;
    private TvSearchResponse similar;
}
