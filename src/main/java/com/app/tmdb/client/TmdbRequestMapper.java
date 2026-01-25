package com.app.tmdb.client;

import com.app.tmdb.requests.*;
import okhttp3.HttpUrl;

public final class TmdbRequestMapper {

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private TmdbRequestMapper() {}

    public static HttpUrl movieDetails(MovieDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("movie")
                .addPathSegment(String.valueOf(r.getMovieId()))
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

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
}
