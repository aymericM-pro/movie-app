package com.app.tmdb.models.responses;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.MoviesByPlatformRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetMoviesByPlatformUseCase extends UseCase<MoviesByPlatformRequest, MovieSearchResponse> {

    private final TmdbClient client;

    @Override
    protected MovieSearchResponse doExecute(MoviesByPlatformRequest request) {
        return client.getMoviesByPlatform(request);
    }
}
