package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvCreditsResponse {
    private List<TvCastResponse> cast;
    private List<TvCrewResponse> crew;
}
