package com.app.tmdb.modules.shows.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.TvCollectionRequest;
import com.app.tmdb.models.responses.MovieSearchResponse;

import com.app.tmdb.modules.shows.responses.TvSearchResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTvCollectionUseCase
        extends UseCase<TvCollectionRequest, TvSearchResponse> {

    private final TmdbClient client;

    @Override
    protected TvSearchResponse doExecute(TvCollectionRequest request) {
        return client.getTvCollection(request);
    }
}
