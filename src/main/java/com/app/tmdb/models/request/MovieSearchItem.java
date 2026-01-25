package com.app.tmdb.models.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSearchItem {

    private long id;
    private String title;
    private String originalTitle;
    private String overview;
    private String releaseDate;
    private String posterPath;
    private Double voteAverage;
}
