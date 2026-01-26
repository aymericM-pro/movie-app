package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MovieCreditsRequest;
import com.app.tmdb.models.responses.MovieFullResponse;
import com.app.tmdb.models.request.MovieDetailsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class GetMovieFullUseCase
        extends UseCase<MovieDetailsRequest, MovieFullResponse> {

    private final TmdbClient client;

    @Override
    protected MovieFullResponse doExecute(MovieDetailsRequest request) {

        CompletableFuture<?> detailsFuture =
                CompletableFuture.supplyAsync(() -> client.getMovieDetails(request));

        CompletableFuture<?> creditsFuture =
                CompletableFuture.supplyAsync(() -> {
                    MovieCreditsRequest r = new MovieCreditsRequest();
                    r.setMovieId(request.getMovieId());
                    r.setLanguage(request.getLanguage());
                    return client.getMovieCredits(r);
                });

        CompletableFuture<?> videosFuture =
                CompletableFuture.supplyAsync(() -> client.getMovieVideos(request));

        CompletableFuture.allOf(detailsFuture, creditsFuture, videosFuture).join();

        MovieFullResponse response = new MovieFullResponse();
        response.setDetails((com.app.tmdb.models.responses.MovieDetailsResponse) detailsFuture.join());
        response.setCredits((com.app.tmdb.models.responses.MovieCreditsTmdbResponse) creditsFuture.join());
        response.setVideos((com.app.tmdb.models.responses.MovieVideosTmdbResponse) videosFuture.join());

        return response;
    }
}
