package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvVideosResponse {

    private Long id;
    private List<TvVideoItemResponse> results;
}