package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.models.request.MovieSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class SearchMoviesUseCase
        extends UseCase<MovieSearchRequest, MovieSearchResponse> {

    private final TmdbClient client;

    public SearchMoviesUseCase(TmdbClient client) {
        this.client = client;
    }

    @Override
    protected MovieSearchResponse doExecute(MovieSearchRequest params) {
        return client.getMovieSearch(params);
    }
}