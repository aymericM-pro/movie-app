package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TvGenreResponse {
    private Integer id;

    @JsonProperty("name")
    private String name;
}
