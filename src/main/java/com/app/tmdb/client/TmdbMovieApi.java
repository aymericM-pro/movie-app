package com.app.tmdb.client;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.enums.*;
import com.app.tmdb.models.request.*;
import com.app.tmdb.models.responses.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TMDB Movies", description = "Endpoints liés aux films TMDB")
@RequestMapping("/tmdb/movies")
public interface TmdbMovieApi {

    @Operation(
            summary = "Détails d’un film",
            description = "Retourne les détails complets d’un film TMDB.",
            parameters = {
                    @Parameter(name = "movieId", description = "ID TMDB du film", required = true),
                    @Parameter(name = "language", description = "Langue (ex: fr-FR, en-US)")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            externalValue = "classpath:swagger/tmdb-movie-details-response.json"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Film introuvable"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/{movieId}")
    ResponseEntity<ApiResult<MovieDetailsResponse>> getMovieDetails(
            @ModelAttribute MovieDetailsRequest request
    );

    @Operation(
            summary = "Listes contenant un film",
            description = "Retourne les listes publiques TMDB dans lesquelles apparaît un film.",
            parameters = {
                    @Parameter(name = "movieId", description = "ID TMDB du film", required = true),
                    @Parameter(name = "page", description = "Numéro de page")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "404", description = "Film introuvable")
            }
    )
    @GetMapping("/{movieId}/lists")
    ResponseEntity<ApiResult<MovieListsResponse>> getMovieLists(
            @ModelAttribute MovieListsRequest request
    );

    @Operation(
            summary = "Recherche de films",
            description = "Recherche un film par titre, description ou mot-clé.",
            parameters = {
                    @Parameter(name = "query", description = "Texte de recherche", required = true),
                    @Parameter(name = "page", description = "Numéro de page"),
                    @Parameter(name = "language", description = "Langue")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Résultats de recherche"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/search")
    ResponseEntity<ApiResult<MovieSearchResponse>> searchMovies(
            @ModelAttribute MovieSearchRequest request
    );

    @Operation(
            summary = "Crédits d’un film",
            description = "Retourne le casting et l’équipe technique d’un film.",
            parameters = {
                    @Parameter(name = "movieId", description = "ID TMDB du film", required = true),
                    @Parameter(name = "language", description = "Langue")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "404", description = "Film introuvable")
            }
    )
    @GetMapping("/{movieId}/credits")
    ResponseEntity<ApiResult<MovieCreditsTmdbResponse>> getMovieCredits(
            MovieCreditsRequest request
    );


    @Operation(
            summary = "Film complet (agrégé)",
            description = "Détails + crédits + vidéos en un seul appel.",
            parameters = {
                    @Parameter(name = "movieId", description = "ID TMDB du film", required = true),
                    @Parameter(name = "language", description = "Langue")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            externalValue = "classpath:swagger/tmdb-movie-full-response.json"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Film introuvable")
            }
    )
    @GetMapping("/{movieId}/full")
    ResponseEntity<ApiResult<MovieFullResponse>> getMovieFull(
            @PathVariable Long movieId,
            @RequestParam(defaultValue = "en-US") String language
    );

    @Operation(
            summary = "Films par plateforme",
            description = "Films disponibles sur une plateforme de streaming via Discover TMDB.",
            parameters = {
                    @Parameter(name = "platform", description = "Plateforme (NETFLIX, DISNEY_PLUS…)", required = true),
                    @Parameter(name = "region", description = "Région (FR, US…)"),
                    @Parameter(name = "language", description = "Langue"),
                    @Parameter(name = "page", description = "Numéro de page")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "400", description = "Plateforme invalide")
            }
    )
    @GetMapping("/platform/{platform}")
    ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByPlatform(
            @PathVariable StreamingPlatform platform,
            @RequestParam(defaultValue = "FR") String region,
            @RequestParam(defaultValue = "en-US") String language,
            @RequestParam(defaultValue = "1") Integer page
    );

    @Operation(
            summary = "Films par genre",
            description = "Films associés à un genre via Discover TMDB.",
            parameters = {
                    @Parameter(name = "genre", description = "Genre du film", required = true),
                    @Parameter(name = "region", description = "Région"),
                    @Parameter(name = "language", description = "Langue"),
                    @Parameter(name = "page", description = "Numéro de page")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "400", description = "Genre invalide")
            }
    )
    @GetMapping("/genre/{genre}")
    ResponseEntity<ApiResult<MovieSearchResponse>> getMoviesByGenre(
            @PathVariable MovieGenre genre,
            @RequestParam(defaultValue = "FR") String region,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    );

    @Operation(
            summary = "Tendances TMDB",
            description = "Films ou séries en tendance (jour ou semaine).",
            parameters = {
                    @Parameter(name = "mediaType", description = "movie | tv | all", required = true),
                    @Parameter(name = "timeWindow", description = "DAY ou WEEK"),
                    @Parameter(name = "language", description = "Langue"),
                    @Parameter(name = "page", description = "Numéro de page")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/trending/{mediaType}")
    ResponseEntity<ApiResult<MovieSearchResponse>> getTrending(
            @PathVariable MediaType mediaType,
            @RequestParam(defaultValue = "DAY") TimeWindow timeWindow,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    );

    @Operation(
            summary = "Séries récentes / à venir",
            description = """
                Retourne des collections de séries TV :
                - RECENT : actuellement diffusées
                - AIRING_TODAY : diffusées aujourd’hui
                - POPULAR : séries populaires
                """,
            parameters = {
                    @Parameter(name = "type", required = true),
                    @Parameter(name = "language"),
                    @Parameter(name = "page")
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Succès"),
                    @ApiResponse(responseCode = "400", description = "Type invalide")
            }
    )
    @GetMapping("/tv/{type}")
    ResponseEntity<ApiResult<MovieSearchResponse>> getTvCollection(
            @PathVariable TvCollectionType type,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    );

    @Operation(
            summary = "Récupérer une collection par ID",
            description = "Retourne une collection TMDB connue (Harry Potter, Star Wars, etc.).",
            parameters = {
                    @Parameter(
                            name = "collectionId",
                            description = "ID TMDB de la collection",
                            required = true
                    ),
                    @Parameter(
                            name = "language",
                            description = "Langue (ex: fr-FR, en-US)"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject(
                                            name = "COLLECTION_RESPONSE",
                                            externalValue = "classpath:swagger/tmdb-collection-response.json"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Collection introuvable"),
                    @ApiResponse(responseCode = "429", description = "Rate limit TMDB")
            }
    )
    @GetMapping("/collections/{collectionId}")
    ResponseEntity<ApiResult<CollectionResponse>> getCollectionById(
            @PathVariable Long collectionId,
            @RequestParam(defaultValue = "fr-FR") String language
    );

}
