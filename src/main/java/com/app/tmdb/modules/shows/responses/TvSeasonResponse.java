package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvSeasonResponse {
    private Integer season_number;
    private String name;
    private String overview;
    private String air_date;
    private String poster_path;
    private Integer episode_count;
}
