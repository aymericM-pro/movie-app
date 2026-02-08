package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MoviesByGenreRequest;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMoviesByGenreUseCase
        extends UseCase<MoviesByGenreRequest, MovieSearchResponse> {

    private final TmdbClient client;

    @Override
    protected MovieSearchResponse doExecute(MoviesByGenreRequest request) {
        return client.getMoviesByGenre(request);
    }
}