package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MovieCreditsRequest;
import com.app.tmdb.models.request.MovieDetailsRequest;
import com.app.tmdb.models.responses.MovieCreditsTmdbResponse;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.responses.MovieFullResponse;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.models.responses.MovieVideosTmdbResponse;
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

        CompletableFuture<MovieDetailsResponse> detailsFuture =
                CompletableFuture.supplyAsync(() -> client.getMovieDetails(request));

        CompletableFuture<MovieCreditsTmdbResponse> creditsFuture =
                CompletableFuture.supplyAsync(() -> {
                    MovieCreditsRequest r = new MovieCreditsRequest();
                    r.setMovieId(request.getMovieId());
                    r.setLanguage(request.getLanguage());
                    return client.getMovieCredits(r);
                });

        CompletableFuture<MovieVideosTmdbResponse> videosFuture =
                CompletableFuture.supplyAsync(() -> client.getMovieVideos(request));

        CompletableFuture<MovieSearchResponse> similarFuture =
                CompletableFuture.supplyAsync(() -> client.getSimilarMovies(request));

        CompletableFuture.allOf(detailsFuture, creditsFuture, videosFuture, similarFuture).join();

        MovieFullResponse response = new MovieFullResponse();
        response.setDetails(detailsFuture.join());
        response.setCredits(creditsFuture.join());
        response.setVideos(videosFuture.join());
        response.setSimilar(similarFuture.join());

        return response;
    }
}
