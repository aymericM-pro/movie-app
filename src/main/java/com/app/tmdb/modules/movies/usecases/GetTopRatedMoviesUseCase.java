package com.app.tmdb.modules.movies.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.modules.movies.requests.TopRatedMoviesRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetTopRatedMoviesUseCase extends UseCase<TopRatedMoviesRequest, MovieSearchResponse> {

    private final TmdbClient tmdbClient;

    @Override
    protected MovieSearchResponse doExecute(TopRatedMoviesRequest params) {
        return tmdbClient.getTopRatedMovies(params);
    }
}
