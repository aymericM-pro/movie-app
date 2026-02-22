package com.app.tmdb.modules.lists.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "movie_list_items",
        uniqueConstraints = @UniqueConstraint(columnNames = {"movie_list_id", "tmdb_movie_id"})
)
public class MovieListItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_list_id", nullable = false)
    private MovieListEntity movieList;

    @Column(name = "tmdb_movie_id", nullable = false)
    private Long tmdbMovieId;

    @Column(name = "added_at", nullable = false)
    private Instant addedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public MovieListEntity getMovieList() {
        return movieList;
    }

    public void setMovieList(MovieListEntity movieList) {
        this.movieList = movieList;
    }

    public Long getTmdbMovieId() {
        return tmdbMovieId;
    }

    public void setTmdbMovieId(Long tmdbMovieId) {
        this.tmdbMovieId = tmdbMovieId;
    }

    public Instant getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Instant addedAt) {
        this.addedAt = addedAt;
    }
}
