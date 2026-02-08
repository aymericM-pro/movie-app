package com.app.tmdb.modules.search.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.modules.search.requests.DiscoverMoviesRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscoverMoviesUseCase
        extends UseCase<DiscoverMoviesRequest, MovieSearchResponse> {

    private final TmdbClient tmdbClient;

    @Override
    protected MovieSearchResponse doExecute(DiscoverMoviesRequest request) {
        return tmdbClient.discoverMovies(request);
    }
}
