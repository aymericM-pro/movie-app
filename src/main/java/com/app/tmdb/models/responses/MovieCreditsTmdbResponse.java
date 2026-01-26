package com.app.tmdb.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieCreditsTmdbResponse {

    private Long id;
    private List<Cast> cast;
    private List<Crew> crew;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Cast {
        private Long id;
        private String name;
        private String character;
        private Integer order;
        private String profile_path;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Crew {
        private Long id;
        private String name;
        private String job;
        private String department;
        private String profile_path;
    }
}

