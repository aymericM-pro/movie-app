package com.app.tmdb.client;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.TmdbError;
import com.app.tmdb.models.request.*;
import com.app.tmdb.models.responses.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import okhttp3.Headers;
import okhttp3.Response;

@Component
public class TmdbClient extends AbstractOkHttpClient {

    public TmdbClient(okhttp3.OkHttpClient client, ObjectMapper mapper) {
        super(client, mapper);
    }

    public MovieListsResponse getMovieLists(MovieListsRequest request) throws BusinessException {
        return get(TmdbRequestMapper.movieLists(request), Headers.of(), MovieListsResponse.class);
    }

    public MovieSearchResponse getMovieSearch(MovieSearchRequest request) throws BusinessException {
        return get(TmdbRequestMapper.searchMovies(request), Headers.of(), MovieSearchResponse.class);
    }

    public MovieDetailsResponse getMovieDetails(MovieDetailsRequest r) {
        return get(TmdbRequestMapper.movieDetails(r), Headers.of(), MovieDetailsResponse.class);
    }

    public MovieCreditsTmdbResponse getMovieCredits(MovieCreditsRequest r) {
        return get(TmdbRequestMapper.movieCredits(r), Headers.of(), MovieCreditsTmdbResponse.class);
    }

    public MovieVideosTmdbResponse getMovieVideos(MovieDetailsRequest r) {
        return get(TmdbRequestMapper.movieVideos(r), Headers.of(), MovieVideosTmdbResponse.class);
    }

    public MovieSearchResponse getMoviesByPlatform(MoviesByPlatformRequest request) {
        return get(TmdbRequestMapper.discoverMoviesByPlatform(request), Headers.of(), MovieSearchResponse.class);
    }

    public MovieSearchResponse getMoviesByGenre(MoviesByGenreRequest request) {
        return get(TmdbRequestMapper.moviesByGenre(request), Headers.of(), MovieSearchResponse.class);
    }

    public MovieSearchResponse getTrending(TrendingRequest request) {
        return get(TmdbRequestMapper.trending(request), Headers.of(), MovieSearchResponse.class);
    }

    public MovieSearchResponse getTvCollection(TvCollectionRequest request) {
        return get(TmdbRequestMapper.tvCollection(request), Headers.of(), MovieSearchResponse.class);
    }

    public CollectionResponse getCollectionById(Long collectionId, String language) {
        return get(TmdbRequestMapper.collectionById(collectionId, language), Headers.of(), CollectionResponse.class
        );
    }


    @Override
    protected RuntimeException mapError(Response response) {
        return switch (response.code()) {
            case 401 -> new BusinessException(TmdbError.UNAUTHORIZED);
            case 404 -> new BusinessException(TmdbError.MOVIE_NOT_FOUND);
            case 429 -> new BusinessException(TmdbError.RATE_LIMIT_EXCEEDED);
            default -> new BusinessException(TmdbError.TMDB_INTERNAL_ERROR);
        };
    }
}
