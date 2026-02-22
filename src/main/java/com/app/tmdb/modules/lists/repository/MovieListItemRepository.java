package com.app.tmdb.modules.lists.repository;

import com.app.tmdb.modules.lists.entity.MovieListItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MovieListItemRepository extends JpaRepository<MovieListItemEntity, UUID> {
    Optional<MovieListItemEntity> findByMovieListIdAndTmdbMovieId(UUID listId, Long tmdbMovieId);
    boolean existsByMovieListIdAndTmdbMovieId(UUID listId, Long tmdbMovieId);
}
