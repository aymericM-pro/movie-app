package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvItemResponse {

    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("original_name")
    private String original_name;

    @JsonProperty("overview")
    private String overview;

    @JsonProperty("poster_path")
    private String poster_path;

    @JsonProperty("backdrop_path")
    private String backdrop_path;

    @JsonProperty("vote_average")
    private Double vote_average;

    @JsonProperty("vote_count")
    private Integer vote_count;

    @JsonProperty("first_air_date")
    private String first_air_date;

    @JsonProperty("genre_ids")
    private List<Integer> genre_ids;
}
