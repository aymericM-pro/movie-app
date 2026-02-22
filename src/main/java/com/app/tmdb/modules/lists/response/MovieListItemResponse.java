package com.app.tmdb.modules.lists.response;

import com.app.tmdb.modules.lists.entity.MovieListItemEntity;

import java.time.Instant;
import java.util.UUID;

public record MovieListItemResponse(
        UUID id,
        Long tmdbMovieId,
        Instant addedAt
) {
    public static MovieListItemResponse from(MovieListItemEntity entity) {
        return new MovieListItemResponse(
                entity.getId(),
                entity.getTmdbMovieId(),
                entity.getAddedAt()
        );
    }
}
