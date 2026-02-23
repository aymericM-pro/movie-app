package com.app.tmdb.modules.movies;

import com.app.tmdb.ApiResult;
import com.app.tmdb.client.TmdbMovieApi;
import com.app.tmdb.models.enums.*;
import com.app.tmdb.models.request.*;
import com.app.tmdb.models.responses.*;
import com.app.tmdb.models.responses.collections.CollectionDetailsResponse;
import com.app.tmdb.modules.collections.usecases.GetCollectionDetailsUseCase;
import com.app.tmdb.modules.movies.requests.TopRatedMoviesRequest;
import com.app.tmdb.modules.movies.usecases.*;
import com.app.tmdb.modules.search.SearchMoviesUseCase;
import com.app.tmdb.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmdbMovieController implements TmdbMovieApi {

    private final UseCaseExecutor executor;

    @Override
    public ResponseEntity<ApiResult<MovieDetailsResponse>> getMovieDetails(MovieDetailsRequest request) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMovieDetailsUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieListsResponse>> getMovieLists(MovieListsRequest request) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMovieListsUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> searchMovies(MovieSearchRequest request) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(SearchMoviesUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieCreditsTmdbResponse>> getMovieCredits(MovieCreditsRequest request) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMovieCreditsUseCase.class, request),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieFullResponse>> getMovieFull(Long movieId, String language) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMovieFullUseCase.class,
                        MovieDetailsRequest.of(movieId, language, null, null)),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByCatalogSource(
            CatalogSourceType type, Integer sourceId, String region, String language, Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMoviesByCatalogSourceUseCase.class,
                        MoviesByCatalogSourceRequest.of(type, sourceId, language, region, page)),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByGenre(
            Integer genreId, String region, String language, Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetMoviesByGenreUseCase.class,
                        MoviesByGenreRequest.of(genreId, language, region, page)),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getTrending(
            MediaType mediaType, TimeWindow timeWindow, String language, Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetTrendingUseCase.class,
                        TrendingRequest.of(mediaType, timeWindow, language, page)),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<CollectionDetailsResponse>> getCollectionDetails(
            Long collectionId, String language) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetCollectionDetailsUseCase.class,
                        CollectionByIdRequest.of(collectionId, language, null, null)),
                HttpStatus.OK.value()
        ));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getTopRatedMovies(String language, Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetTopRatedMoviesUseCase.class,
                        TopRatedMoviesRequest.of(language != null ? language : "fr-FR", "FR", page)),
                HttpStatus.OK.value()
        ));
    }
}