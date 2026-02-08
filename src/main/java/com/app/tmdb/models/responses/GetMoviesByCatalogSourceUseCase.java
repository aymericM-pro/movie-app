package com.app.tmdb.models.responses;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MoviesByCatalogSourceRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMoviesByCatalogSourceUseCase
        extends UseCase<MoviesByCatalogSourceRequest, MovieSearchResponse> {

    private final TmdbClient client;

    @Override
    protected MovieSearchResponse doExecute(MoviesByCatalogSourceRequest request) {
        return client.getMoviesByCatalogSource(request);
    }
}