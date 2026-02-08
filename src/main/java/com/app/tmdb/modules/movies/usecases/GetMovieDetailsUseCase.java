package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.request.MovieDetailsRequest;
import com.app.tmdb.usecase.UseCase;
import org.springframework.stereotype.Component;

@Component
public class GetMovieDetailsUseCase
        extends UseCase<MovieDetailsRequest, MovieDetailsResponse> {

    private final TmdbClient client;

    public GetMovieDetailsUseCase(TmdbClient client) {
        this.client = client;
    }

    @Override
    protected MovieDetailsResponse doExecute(MovieDetailsRequest params) {
        return client.getMovieDetails(params);
    }
}