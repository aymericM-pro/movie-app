package com.app.tmdb.client;

import com.app.tmdb.errors.BusinessException;
import com.app.tmdb.errors.TmdbError;
import com.app.tmdb.models.request.*;
import com.app.tmdb.models.responses.*;
import com.app.tmdb.modules.genders.responses.Gender;
import com.app.tmdb.modules.genders.responses.GenderResponse;
import com.app.tmdb.modules.genders.responses.GenreListResponse;
import com.app.tmdb.modules.movies.requests.TopRatedMoviesRequest;
import com.app.tmdb.modules.collections.responses.CollectionSearchResponse;
import com.app.tmdb.modules.search.requests.DiscoverMoviesRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import okhttp3.Headers;
import okhttp3.Response;

import java.util.List;

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

    public MovieSearchResponse getMoviesByCatalogSource(MoviesByCatalogSourceRequest request) {
        return get(TmdbRequestMapper.discoverMoviesByCatalogSource(request), Headers.of(), MovieSearchResponse.class);
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

    public CollectionSearchResponse searchCollections(String query, String language) {
        return get(TmdbRequestMapper.searchCollections(query, language), Headers.of(), CollectionSearchResponse.class);
    }

    public MovieSearchResponse getTopRatedMovies(TopRatedMoviesRequest request) {
        return get(TmdbRequestMapper.topRatedMovies(request), Headers.of(), MovieSearchResponse.class);
    }

    public List<Gender> getGenres(String language) {
        return get(TmdbRequestMapper.genres(language), Headers.of(), GenreListResponse.class).getGenres();
    }

    public MovieSearchResponse discoverMovies(DiscoverMoviesRequest request) {
        return get(TmdbRequestMapper.discoverMovies(request), Headers.of(), MovieSearchResponse.class);
    }

    public MovieSearchResponse getSimilarMovies(MovieDetailsRequest r) {
        return get(TmdbRequestMapper.similarMovies(r), Headers.of(), MovieSearchResponse.class);
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
