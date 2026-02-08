package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieListsResponse;
import com.app.tmdb.models.request.MovieListsRequest;
import com.app.tmdb.usecase.UseCase;
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