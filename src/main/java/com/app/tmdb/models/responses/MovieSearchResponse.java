package com.app.tmdb.models.responses;

import com.app.tmdb.models.request.MovieSearchItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchResponse {

    private int page;
    private List<MovieSearchItem> results;
    private int totalPages;
    private int totalResults;
}