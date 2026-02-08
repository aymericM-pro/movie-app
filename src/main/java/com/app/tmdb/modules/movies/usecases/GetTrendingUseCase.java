package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.TrendingRequest;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTrendingUseCase
        extends UseCase<TrendingRequest, MovieSearchResponse> {

    private final TmdbClient client;

    @Override
    protected MovieSearchResponse doExecute(TrendingRequest request) {
        return client.getTrending(request);
    }
}