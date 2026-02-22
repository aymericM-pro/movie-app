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
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByCatalogSource(
            @RequestParam CatalogSourceType type,
            @RequestParam Integer sourceId,
            @RequestParam(defaultValue = "FR") String region,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        MoviesByCatalogSourceRequest request = new MoviesByCatalogSourceRequest();
        request.setType(type);
        request.setSourceId(sourceId);
        request.setRegion(region);
        request.setLanguage(language);
        request.setPage(page);

        MovieSearchResponse result =
                executor.execute(GetMoviesByCatalogSourceUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }

    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByGenre(
            Integer genreId,
            String region,
            String language,
            Integer page
    ) {
        MoviesByGenreRequest request = new MoviesByGenreRequest();
        request.setGenreId(genreId);
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

/*    @Override
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
    }*/

    @Override
    public ResponseEntity<ApiResult<CollectionDetailsResponse>> getCollectionDetails(
            @PathVariable Long collectionId,
            @RequestParam(defaultValue = "fr-FR") String language
    ) {
        CollectionByIdRequest request = new CollectionByIdRequest();
        request.setCollectionId(collectionId);
        request.setLanguage(language);

        return ResponseEntity.ok(
                ApiResult.from(
                        executor.execute(GetCollectionDetailsUseCase.class, request),
                        HttpStatus.OK.value()
                )
        );
    }


    @Override
    public ResponseEntity<ApiResult<MovieSearchResponse>> getTopRatedMovies(
            @RequestParam(required = false) String language,
            @RequestParam(defaultValue = "1") Integer page
    ) {
        TopRatedMoviesRequest request = new TopRatedMoviesRequest();
        request.setLanguage(language != null ? language : "fr-FR");
        request.setPage(page);
        request.setRegion("FR");

        MovieSearchResponse result =
                executor.execute(GetTopRatedMoviesUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(result, HttpStatus.OK.value()));
    }
}
