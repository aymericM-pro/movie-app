package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.TvCollectionRequest;
import com.app.tmdb.models.responses.MovieSearchResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTvCollectionUseCase
        extends UseCase<TvCollectionRequest, MovieSearchResponse> {

    private final TmdbClient client;

    @Override
    protected MovieSearchResponse doExecute(TvCollectionRequest request) {
        return client.getTvCollection(request);
    }
}
