package com.app.tmdb.modules.shows.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.modules.shows.requests.TvDetailsRequest;
import com.app.tmdb.modules.shows.responses.TvDetailsResponse;
import com.app.tmdb.modules.shows.responses.TvFullResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetTvFullUseCase extends UseCase<TvDetailsRequest, TvFullResponse> {

    private final TmdbClient client;

    @Override
    protected TvFullResponse doExecute(TvDetailsRequest request) {

        var detailsFuture  = CompletableFuture.supplyAsync(() -> client.getTvDetails(request));
        var creditsFuture  = CompletableFuture.supplyAsync(() -> client.getTvCredits(request));
        var videosFuture   = CompletableFuture.supplyAsync(() -> client.getTvVideos(request));
        var imagesFuture   = CompletableFuture.supplyAsync(() -> client.getTvImages(request));
        var similarFuture  = CompletableFuture.supplyAsync(() -> client.getSimilarTv(request));

        CompletableFuture.allOf(detailsFuture, creditsFuture, videosFuture, imagesFuture, similarFuture).join();

        TvFullResponse response = new TvFullResponse();
        response.setDetails(detailsFuture.join());
        response.setCredits(creditsFuture.join());
        response.setVideos(videosFuture.join());
        response.setImages(imagesFuture.join());
        response.setSimilar(similarFuture.join());

        return response;
    }
}

