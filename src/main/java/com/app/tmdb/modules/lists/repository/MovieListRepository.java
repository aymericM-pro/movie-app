package com.app.tmdb.modules.lists.repository;

import com.app.tmdb.modules.lists.entity.MovieListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieListRepository extends JpaRepository<MovieListEntity, UUID> {
    List<MovieListEntity> findAllByUserEmail(String email);
    Optional<MovieListEntity> findByIdAndUserEmail(UUID id, String email);
}
