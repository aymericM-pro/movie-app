package com.app.tmdb.client;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.responses.MovieDetailsResponse;
import com.app.tmdb.models.responses.MovieListsResponse;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.requests.MovieDetailsRequest;
import com.app.tmdb.requests.MovieListsRequest;
import com.app.tmdb.requests.MovieSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TMDB Movies", description = "Endpoints liés aux films TMDB")
@RequestMapping("/tmdb/movies")
public interface TmdbMovieApi {

    // ----------------------------------------------------------------
    // MOVIE DETAILS
    // ----------------------------------------------------------------

    @Operation(
            summary = "Détails d’un film",
            description = "Retourne les détails complets d’un film TMDB.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "MOVIE_DETAILS_RESPONSE",
                                            externalValue = "classpath:swagger/tmdb-movie-details-response.json"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Token TMDB invalide"),
                    @ApiResponse(responseCode = "404", description = "Film introuvable"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/{movieId}")
    ResponseEntity<ApiResult<MovieDetailsResponse>> getMovieDetails(
            @ModelAttribute MovieDetailsRequest request
    );

    // ----------------------------------------------------------------
    // MOVIE LISTS
    // ----------------------------------------------------------------

    @Operation(
            summary = "Liste les listes contenant un film",
            description = "Retourne les listes publiques TMDB dans lesquelles apparaît un film.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "MOVIE_LISTS_RESPONSE",
                                            externalValue = "classpath:swagger/tmdb-movie-lists-response.json"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "401", description = "Token TMDB invalide"),
                    @ApiResponse(responseCode = "404", description = "Film introuvable"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/{movieId}/lists")
    ResponseEntity<ApiResult<MovieListsResponse>> getMovieLists(
            @ModelAttribute MovieListsRequest request
    );

    // ----------------------------------------------------------------
    // SEARCH
    // ----------------------------------------------------------------

    @Operation(
            summary = "Recherche de films",
            description = "Recherche un film par titre, description ou mot-clé.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Résultats de recherche"),
                    @ApiResponse(responseCode = "401", description = "Token TMDB invalide"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/search")
    ResponseEntity<ApiResult<MovieSearchResponse>> searchMovies(
            @ModelAttribute MovieSearchRequest request
    );
}
