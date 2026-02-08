package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MovieCreditsRequest;
import com.app.tmdb.models.responses.MovieCreditsTmdbResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMovieCreditsUseCase extends UseCase<MovieCreditsRequest, MovieCreditsTmdbResponse> {

    private final TmdbClient client;

    @Override
    protected MovieCreditsTmdbResponse doExecute(MovieCreditsRequest request) {
        return client.getMovieCredits(request);
    }
}
