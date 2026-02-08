package com.app.tmdb.models.responses.collections;

import lombok.Data;

import java.util.List;

@Data
public class CollectionDetailsResponse {

    private Long id;
    private String name;
    private String overview;
    private String posterPath;
    private String backdropPath;

    private List<CollectionMovieDetails> movies;

    private CollectionStats stats;

    private CollectionMeta meta;
}