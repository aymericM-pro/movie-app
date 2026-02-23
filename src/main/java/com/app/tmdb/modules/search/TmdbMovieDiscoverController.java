package com.app.tmdb.modules.search;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.responses.MovieSearchResponse;
import com.app.tmdb.modules.search.requests.DiscoverMoviesRequest;
import com.app.tmdb.modules.search.usecases.DiscoverMoviesUseCase;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tmdb/movies")
public class TmdbMovieDiscoverController {

    private final UseCaseExecutor executor;

    @GetMapping("/discover")
    public ResponseEntity<ApiResult<MovieSearchResponse>> discoverMovies(
            @RequestParam(required = false) List<Integer> genres,
            @RequestParam(required = false) BigDecimal voteAverageMin,
            @RequestParam(required = false) BigDecimal voteAverageMax,
            @RequestParam(required = false) Integer voteCountMin,
            @RequestParam(required = false) Integer runtimeMin,
            @RequestParam(required = false) Integer runtimeMax,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "fr-FR") String language,
            @RequestParam(defaultValue = "FR") String region,
            @RequestParam(defaultValue = "1") Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(DiscoverMoviesUseCase.class,
                        DiscoverMoviesRequest.of(genres, voteAverageMin, voteAverageMax,
                                voteCountMin, runtimeMin, runtimeMax, sortBy, language, region, page)),
                HttpStatus.OK.value()
        ));
    }

}
