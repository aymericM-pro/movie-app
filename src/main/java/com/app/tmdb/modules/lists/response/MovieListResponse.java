package com.app.tmdb.modules.lists.response;

import com.app.tmdb.modules.lists.entity.MovieListEntity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record MovieListResponse(
        UUID id,
        String name,
        Instant createdAt,
        List<MovieListItemResponse> items
) {
    public static MovieListResponse from(MovieListEntity entity) {
        return new MovieListResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getItems().stream().map(MovieListItemResponse::from).toList()
        );
    }

    public static MovieListResponse fromWithoutItems(MovieListEntity entity) {
        return new MovieListResponse(
                entity.getId(),
                entity.getName(),
                entity.getCreatedAt(),
                null
        );
    }
}
