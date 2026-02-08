package com.app.tmdb.models.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDetailsResponse {

    private Long id;
    private String title;

    @JsonProperty("original_title")
    private String originalTitle;

    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("backdrop_path")
    private String backdropPath;

    @JsonProperty("release_date")
    private String releaseDate;

    @JsonProperty("vote_average")
    private BigDecimal voteAverage;

    @JsonProperty("vote_count")
    private Integer voteCount;

    private Long budget;
    private Long revenue;
    private Integer runtime;

    @JsonProperty("genres")
    private List<GenreRef> genres;

    @JsonProperty("production_countries")
    private List<ProductionCountryRef> productionCountries;

    @JsonProperty("spoken_languages")
    private List<SpokenLanguageRef> spokenLanguages;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GenreRef {

        private Integer id;
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ProductionCountryRef {
        private String name;
        private String iso_3166_1;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SpokenLanguageRef {
        @JsonProperty("english_name")
        private String englishName;
        @JsonProperty("iso_639_1")
        private String iso6391;
    }
}
