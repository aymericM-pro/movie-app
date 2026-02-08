package com.app.tmdb.modules.shows.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvVideoItemResponse {
    private String id;
    private String key;
    private String name;
    private String site;
}