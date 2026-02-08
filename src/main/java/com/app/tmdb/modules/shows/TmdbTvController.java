package com.app.tmdb.modules.shows;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.enums.TvCollectionType;
import com.app.tmdb.models.request.TvCollectionRequest;
import com.app.tmdb.modules.shows.requests.TvDetailsRequest;
import com.app.tmdb.modules.shows.responses.TvDetailsResponse;
import com.app.tmdb.modules.shows.responses.TvFullResponse;
import com.app.tmdb.modules.shows.responses.TvSearchResponse;
import com.app.tmdb.modules.shows.usecases.GetTvCollectionUseCase;
import com.app.tmdb.modules.shows.usecases.GetTvFullUseCase;
import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TmdbTvController implements TmdbTvApi {

    private final UseCaseExecutor executor;

    @Override
    public ResponseEntity<ApiResult<TvSearchResponse>> getTvCollection(
            TvCollectionType type,
            String language,
            Integer page
    ) {
        TvCollectionRequest request = new TvCollectionRequest();
        request.setType(type);
        request.setLanguage(language);
        request.setPage(page);

        TvSearchResponse result = executor.execute(GetTvCollectionUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(result, 200));
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<ApiResult<TvFullResponse>> getTvDetails(
            @PathVariable Long id,
            @RequestParam(defaultValue = "fr-FR") String language
    ) {
        TvDetailsRequest request = new TvDetailsRequest(id, language);
        TvFullResponse response =
                executor.execute(GetTvFullUseCase.class, request);

        return ResponseEntity.ok(ApiResult.from(response, 200));
    }

}
