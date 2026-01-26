package com.app.tmdb.models.responses;

import lombok.Data;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieVideosTmdbResponse {

    private Long id;
    private List<Video> results;

    @Data
    public static class Video {
        private String id;
        private String key;
        private String name;
        private String site;
        private String type;
    }
}