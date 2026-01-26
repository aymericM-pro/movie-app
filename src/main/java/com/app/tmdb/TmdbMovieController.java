package com.app.tmdb;

import com.app.tmdb.client.TmdbMovieApi;
import com.app.tmdb.models.enums.*;
import com.app.tmdb.models.request.*;
import com.app.tmdb.models.responses.*;
import com.app.tmdb.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmdbMovieController implements TmdbMovieApi {

    private final UseCaseExecutor executor;

    @Override
    public ResponseEntity<ApiResult<MovieDetailsResponse>> getMovieDetails(MovieDetailsRequest request) {
        MovieDetailsResponse result = executor.execute(GetMovieDetailsUseCase.class, request);
        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieListsResponse>> getMovieLists(MovieListsRequest request) {
        MovieListsResponse result = executor.execute(GetMovieListsUseCase.class, request);
        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> searchMovies(MovieSearchRequest request) {
        MovieSearchResponse result = executor.execute(SearchMoviesUseCase.class, request);
        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieCreditsTmdbResponse>> getMovieCredits(MovieCreditsRequest request) {
        MovieCreditsTmdbResponse result = executor.execute(GetMovieCreditsUseCase.class, request);
        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieFullResponse>> getMovieFull(Long movieId, String language) {
        MovieDetailsRequest request = new MovieDetailsRequest();
        request.setMovieId(movieId);
        request.setLanguage(language);

        MovieFullResponse result = executor.execute(GetMovieFullUseCase.class, request);
        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByPlatform(StreamingPlatform platform, String region, String language, Integer page) {
        MoviesByPlatformRequest request = new MoviesByPlatformRequest();
        request.setPlatform(platform);
        request.setRegion(region);
        request.setLanguage(language);
        request.setPage(page);

        MovieSearchResponse result = executor.execute(GetMoviesByPlatformUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByGenre(
            @PathVariable MovieGenre genre,
            @RequestParam(defaultValue = "FR") String region,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        MoviesByGenreRequest request = new MoviesByGenreRequest();
        request.setGenre(genre);
        request.setRegion(region);
        request.setLanguage(language);
        request.setPage(page);

        MovieSearchResponse result =
                executor.execute(GetMoviesByGenreUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getTrending(
            @PathVariable MediaType mediaType,
            @RequestParam(defaultValue = "DAY") TimeWindow timeWindow,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        TrendingRequest request = new TrendingRequest();
        request.setMediaType(mediaType);
        request.setTimeWindow(timeWindow);
        request.setLanguage(language);
        request.setPage(page);

        return ResponseEntity.ok(ApiResult.from(executor.execute(GetTrendingUseCase.class, request), HttpStatus.OK.value())
        );
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getTvCollection(
            @PathVariable TvCollectionType type,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        TvCollectionRequest request = new TvCollectionRequest();
        request.setType(type);
        request.setLanguage(language);
        request.setPage(page);

        return ResponseEntity.ok(
                ApiResult.from(
                        executor.execute(GetTvCollectionUseCase.class, request),
                        200
                )
        );
    }

    @Override
    public ResponseEntity<ApiResult<CollectionResponse>> getCollectionById(
            @PathVariable Long collectionId,
            @RequestParam(defaultValue = "fr-FR") String language
    ) {
        CollectionByIdRequest request = new CollectionByIdRequest();
        request.setCollectionId(collectionId);
        request.setLanguage(language);

        return ResponseEntity.ok(ApiResult.from(executor.execute(GetCollectionByIdUseCase.class, request), 200));
    }
}
