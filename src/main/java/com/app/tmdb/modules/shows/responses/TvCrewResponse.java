package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvCrewResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("credit_id")
    private String creditId;

    @JsonProperty("department")
    private String department;

    @JsonProperty("job")
    private String job;

    @JsonProperty("profile_path")
    private String profilePath;
}
