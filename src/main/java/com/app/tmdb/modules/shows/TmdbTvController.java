package com.app.tmdb.modules.shows;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.enums.TvCollectionType;
import com.app.tmdb.models.request.TvCollectionRequest;
import com.app.tmdb.modules.shows.requests.TvDetailsRequest;
import com.app.tmdb.modules.shows.responses.TvFullResponse;
import com.app.tmdb.modules.shows.responses.TvSearchResponse;
import com.app.tmdb.modules.shows.usecases.GetTvCollectionUseCase;
import com.app.tmdb.modules.shows.usecases.GetTvFullUseCase;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmdbTvController implements TmdbTvApi {

    private final UseCaseExecutor executor;

    @Override
    public ResponseEntity<ApiResult<TvSearchResponse>> getTvCollection(
            TvCollectionType type, String language, Integer page) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetTvCollectionUseCase.class,
                        TvCollectionRequest.of(type, language, page)),
                200
        ));
    }

    @Override
    public ResponseEntity<ApiResult<TvFullResponse>> getTvDetails(Long id, String language) {
        return ResponseEntity.ok(ApiResult.from(
                executor.execute(GetTvFullUseCase.class, TvDetailsRequest.of(id, language)),
                200
        ));
    }
}
