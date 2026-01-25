package com.app.tmdb;

import com.app.tmdb.client.TmdbMovieApi;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.responses.MovieListsResponse;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.requests.MovieDetailsRequest;
import com.app.tmdb.requests.MovieListsRequest;
import com.app.tmdb.requests.MovieSearchRequest;
import com.app.tmdb.usecase.GetMovieDetailsUseCase;
import com.app.tmdb.usecase.GetMovieListsUseCase;
import com.app.tmdb.usecase.SearchMoviesUseCase;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmdbMovieController implements TmdbMovieApi {

    private final UseCaseExecutor executor;

    @Override
    public ResponseEntity<ApiResult<MovieDetailsResponse>> getMovieDetails(
            MovieDetailsRequest request
    ) {
        MovieDetailsResponse result =
                executor.execute(GetMovieDetailsUseCase.class, request);

        return ResponseEntity.ok(
                ApiResult.from(result, HttpStatus.OK.value())
        );
    }

    @Override
    public ResponseEntity<ApiResult<MovieListsResponse>> getMovieLists(
            MovieListsRequest request
    ) {
        MovieListsResponse result =
                executor.execute(GetMovieListsUseCase.class, request);

        return ResponseEntity.ok(
                ApiResult.from(result, HttpStatus.OK.value())
        );
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> searchMovies(
            MovieSearchRequest request
    ) {
        MovieSearchResponse result =
                executor.execute(SearchMoviesUseCase.class, request);

        return ResponseEntity.ok(
                ApiResult.from(result, HttpStatus.OK.value())
        );
    }
}
