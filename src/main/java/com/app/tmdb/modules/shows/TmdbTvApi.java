package com.app.tmdb.modules.shows;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.enums.TvCollectionType;
import com.app.tmdb.modules.shows.responses.TvFullResponse;
import com.app.tmdb.modules.shows.responses.TvSearchResponse;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "TMDB TV", description = "Endpoints liés aux séries TV TMDB")
@RequestMapping("/tmdb/tv")
public interface TmdbTvApi {

    @Operation(
            summary = "Collections de séries TV",
            description = "Retourne des séries TV TMDB",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TvSearchResponse.class)
                            )
                    )
            }
    )
    @GetMapping("/{type}")
    ResponseEntity<ApiResult<TvSearchResponse>> getTvCollection(
            @PathVariable TvCollectionType type,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "1") Integer page
    );

    @Operation(
            summary = "Détails complets d’une série TV",
            description = "Retourne les détails, crédits, vidéos, images et séries similaires d’une série TV TMDB.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Succès",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = TvFullResponse.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Série TV introuvable"),
                    @ApiResponse(responseCode = "429", description = "Limite TMDB atteinte")
            }
    )
    @GetMapping("/{id}/details")
    ResponseEntity<ApiResult<TvFullResponse>> getTvDetails(
            @PathVariable Long id,
            @RequestParam(defaultValue = "fr-FR") String language
    );
}
