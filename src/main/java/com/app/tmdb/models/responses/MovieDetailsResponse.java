package com.app.tmdb.models.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailsResponse {

    private long id;
    private String title;
    private String original_title;
    private String overview;
    private String release_date;
    private int runtime;
    private String poster_path;
    private String backdrop_path;
    private double vote_average;
    private int vote_count;
    private String original_language;
}