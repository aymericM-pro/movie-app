package com.app.tmdb.client;

import com.app.tmdb.models.request.*;
import com.app.tmdb.modules.movies.requests.TopRatedMoviesRequest;
import com.app.tmdb.modules.search.requests.DiscoverMoviesRequest;
import okhttp3.HttpUrl;

import java.util.stream.Collectors;

public final class TmdbRequestMapper {

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private TmdbRequestMapper() {}

    public static HttpUrl movieLists(MovieListsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addPathSegment("lists")
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl searchMovies(MovieSearchRequest r) {
        HttpUrl.Builder b = HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("search")
                .addPathSegment("movie")
                .addQueryParameter("query", r.getQuery())
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .addQueryParameter("language", r.getLanguage());

        if (r.getYear() != null) {
            b.addQueryParameter("year", String.valueOf(r.getYear()));
        }
        return b.build();
    }

    public static HttpUrl movieDetails(MovieDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl movieCredits(MovieCreditsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addPathSegment("credits")
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl movieVideos(MovieDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addPathSegment("videos")
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl discoverMoviesByCatalogSource(MoviesByCatalogSourceRequest r) {

        HttpUrl.Builder b = HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegments("discover/movie")
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", String.valueOf(r.getPage()));

        switch (r.getType()) {
            case PROVIDER -> {
                b.addQueryParameter("with_watch_providers", String.valueOf(r.getSourceId()));
                b.addQueryParameter("watch_region", r.getRegion());
            }
            case STUDIO -> {
                b.addQueryParameter("with_companies", String.valueOf(r.getSourceId()));
            }
        }

        return b.build();
    }

    public static HttpUrl moviesByGenre(MoviesByGenreRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegments("discover/movie")
                .addQueryParameter("with_genres", String.valueOf(r.getGenreId()))
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .addQueryParameter("sort_by", "popularity.desc")
                .build();
    }


    public static HttpUrl trending(TrendingRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("trending")
                .addPathSegment(r.getMediaType().name().toLowerCase())
                .addPathSegment(r.getTimeWindow().name().toLowerCase())
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .build();
    }

    public static HttpUrl collectionById(Long collectionId, String language) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("collection")
                .addPathSegment(String.valueOf(collectionId))
                .addQueryParameter("language", language)
                .build();
    }

    public static HttpUrl searchCollections(String query, String language) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("search")
                .addPathSegment("collection")
                .addQueryParameter("query", query)
                .addQueryParameter("language", language)
                .build();
    }

    public static HttpUrl topRatedMovies(TopRatedMoviesRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment("top_rated")
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("region", r.getRegion())
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .build();
    }

    public static HttpUrl genres(String language) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegments("genre/movie/list")
                .addQueryParameter("language", language)
                .build();
    }

    public static HttpUrl discoverMovies(DiscoverMoviesRequest r) {
        HttpUrl.Builder b = HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegments("discover/movie")
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("region", r.getRegion())
                .addQueryParameter("sort_by", r.getSortBy());

        if (!r.getGenres().isEmpty()) {
            b.addQueryParameter(
                    "with_genres",
                    r.getGenres().stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining(","))
            );
        }

        addIfPresent(b, "vote_average.gte", r.getVoteAverageMin());
        addIfPresent(b, "vote_average.lte", r.getVoteAverageMax());
        addIfPresent(b, "vote_count.gte", r.getVoteCountMin());
        addIfPresent(b, "with_runtime.gte", r.getRuntimeMin());
        addIfPresent(b, "with_runtime.lte", r.getRuntimeMax());

        return b.build();
    }

    public static HttpUrl similarMovies(MovieDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addPathSegment("similar")
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", "1")
                .build();
    }

    private static void addIfPresent(HttpUrl.Builder b, String paramName, Object value) {
        if (value != null) {
            b.addQueryParameter(paramName, String.valueOf(value));
        }
    }

}
