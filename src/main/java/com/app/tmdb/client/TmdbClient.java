package com.app.tmdb.client;

import com.app.tmdb.TmdbProperties;
import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.TmdbError;
import com.app.tmdb.models.responses.*;
import com.app.tmdb.requests.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.stereotype.Component;

@Component
public class TmdbClient extends AbstractOkHttpClient {

    public TmdbClient(OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public MovieDetailsResponse getMovieDetails(MovieDetailsRequest r) {
        return get(TmdbRequestMapper.movieDetails(r), Headers.of(), MovieDetailsResponse.class);
    }

    public MovieListsResponse getMovieLists(MovieListsRequest r) {
        return get(TmdbRequestMapper.movieLists(r), Headers.of(), MovieListsResponse.class);
    }

    public MovieSearchResponse searchMovies(MovieSearchRequest r) {
        return get(TmdbRequestMapper.searchMovies(r), Headers.of(), MovieSearchResponse.class);
    }

    @Override
    protected RuntimeException mapError(Response response) {
        return switch (response.code()) {
            case 401 -> new BusinessException(TmdbError.UNAUTHORIZED);
            case 404 -> new BusinessException(TmdbError.MOVIE_NOT_FOUND);
            case 429 -> new BusinessException(TmdbError.RATE_LIMIT_EXCEEDED);
            default -> new BusinessException(TmdbError.TMDB_INTERNAL_ERROR);
        };
    }
}
