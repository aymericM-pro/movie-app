package com.app.tmdb.modules.genders;

import com.app.tmdb.ApiResult;
import com.app.tmdb.modules.genders.requests.GetGenresRequest;
import com.app.tmdb.modules.genders.responses.GenderResponse;
import com.app.tmdb.modules.genders.usecases.GetGenresUseCase;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/genders")
public class TmdbGenreController {

    private final UseCaseExecutor executor;

    @GetMapping
    public ResponseEntity<ApiResult<List<GenderResponse>>> getGenres(
            @RequestParam(defaultValue = "fr-FR") String language) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetGenresUseCase.class, GetGenresRequest.of(language)),
                HttpStatus.OK.value()
        ));
    }

}