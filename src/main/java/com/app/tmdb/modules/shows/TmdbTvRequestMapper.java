package com.app.tmdb.modules.shows;

import com.app.tmdb.models.request.TvCollectionRequest;
import com.app.tmdb.modules.shows.requests.TvDetailsRequest;
import okhttp3.HttpUrl;

public final class TmdbTvRequestMapper {

    private static final String BASE_URL = "https://api.themoviedb.org/3";

    private TmdbTvRequestMapper() {}

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

    public static HttpUrl tvDetails(TvDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(String.valueOf(r.getTvId()))
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl tvCredits(TvDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(String.valueOf(r.getTvId()))
                .addPathSegment("credits")
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl tvVideos(TvDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(String.valueOf(r.getTvId()))
                .addPathSegment("videos")
                .addQueryParameter("language", r.getLanguage())
                .build();
    }

    public static HttpUrl similarTv(TvDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(String.valueOf(r.getTvId()))
                .addPathSegment("similar")
                .addQueryParameter("language", r.getLanguage())
                .addQueryParameter("page", "1")
                .build();
    }

    public static HttpUrl tvImages(TvDetailsRequest r) {
        return HttpUrl.parse(BASE_URL).newBuilder()
                .addPathSegment("tv")
                .addPathSegment(String.valueOf(r.getTvId()))
                .addPathSegment("images")
                .addQueryParameter("include_image_language", "en,null")
                .build();
    }
}
