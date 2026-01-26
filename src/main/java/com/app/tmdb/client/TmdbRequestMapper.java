package com.app.tmdb.client;

import com.app.tmdb.models.request.*;
import okhttp3.HttpUrl;

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

    public static HttpUrl discoverMoviesByPlatform(MoviesByPlatformRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("discover")
                .addPathSegment("movie")
                .addQueryParameter("with_watch_providers",
                        String.valueOf(r.getPlatform().getProviderId()))
                .addQueryParameter("watch_region", r.getRegion())
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", String.valueOf(r.getPage()))
                .build();
    }

    public static HttpUrl moviesByGenre(MoviesByGenreRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegments("discover/movie")
                .addQueryParameter("with_genres", String.valueOf(r.getGenre().getId()))
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("region", r.getRegion())
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

    public static HttpUrl tvCollection(TvCollectionRequest r) {

        String path = switch (r.getType()) {
            case RECENT -> "on_the_air";
            case AIRING_TODAY -> "airing_today";
            case POPULAR -> "popular";
        };

        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(path)
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
}
