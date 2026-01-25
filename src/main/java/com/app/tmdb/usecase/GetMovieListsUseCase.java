package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieListsResponse;
import com.app.tmdb.requests.MovieListsRequest;
import org.springframework.stereotype.Component;

@Component
public class GetMovieListsUseCase
        extends UseCase<MovieListsRequest, MovieListsResponse> {

    private final TmdbClient client;

    public GetMovieListsUseCase(TmdbClient client) {
        this.client = client;
    }

    @Override
    protected MovieListsResponse doExecute(MovieListsRequest params) {
        return client.getMovieLists(params);
    }
}