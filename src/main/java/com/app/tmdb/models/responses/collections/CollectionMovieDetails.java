package com.app.tmdb.models.responses.collections;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class CollectionMovieDetails {

    private Long id;
    private String title;
    private String originalTitle;
    private String overview;

    private String posterPath;
    private String backdropPath;
    private LocalDate releaseDate;

    private BigDecimal voteAverage;
    private Integer voteCount;

    private Long budget;
    private Long revenue;
    private Integer runtime;

    private List<String> genres;
    private List<String> productionCountries;
    private List<String> spokenLanguages;

    private Integer order;
}
