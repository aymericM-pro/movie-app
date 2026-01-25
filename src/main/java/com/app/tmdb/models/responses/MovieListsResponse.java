package com.app.tmdb.models.responses;

import com.app.tmdb.MovieListItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class MovieListsResponse {

    private long id;
    private int page;

    private List<MovieListItem> results;

    @JsonProperty("total_pages")
    private int totalPages;

    @JsonProperty("total_results")
    private int totalResults;
}
