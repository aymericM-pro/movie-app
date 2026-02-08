package com.app.tmdb.modules.shows.responses;

import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvMainDetails {

    private Long id;
    private String name;
    private String original_name;
    private String overview;

    private String poster_path;
    private String backdrop_path;

    private Double vote_average;
    private Integer vote_count;

    private String first_air_date;
    private String last_air_date;

    private Integer number_of_seasons;
    private Integer number_of_episodes;

    private List<TvGenreResponse> genres;
    private List<TvSeasonResponse> seasons;
}

