package com.app.tmdb.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieFullResponse {

    private MovieDetailsResponse details;
    private MovieCreditsTmdbResponse credits;
    private MovieVideosTmdbResponse videos;
}