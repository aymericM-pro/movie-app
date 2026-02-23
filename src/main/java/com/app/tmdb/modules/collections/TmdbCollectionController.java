package com.app.tmdb.modules.collections;

import com.app.tmdb.ApiResult;
import com.app.tmdb.models.responses.CollectionResponse;
import com.app.tmdb.modules.collections.request.GetCollectionByIdRequest;
import com.app.tmdb.modules.collections.request.GetPopularCollectionsRequest;
import com.app.tmdb.modules.collections.responses.PopularCollectionResponse;
import com.app.tmdb.modules.collections.usecases.GetPopularCollectionsUseCase;
import com.app.tmdb.modules.collections.usecases.GetCollectionByIdUseCase;

import com.app.tmdb.usecase.UseCaseExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class TmdbCollectionController {

    private final UseCaseExecutor executor;

    @GetMapping("/popular")
    public ResponseEntity<ApiResult<List<PopularCollectionResponse>>> getPopularCollections(
            @RequestParam(defaultValue = "fr-FR") String language
    ) {
        return ResponseEntity.ok(
                ApiResult.from(
                        executor.execute(GetPopularCollectionsUseCase.class, GetPopularCollectionsRequest.of(language)),
                        HttpStatus.OK.value()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResult<CollectionResponse>> getCollectionById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "fr-FR") String language
    ) {
        return ResponseEntity.ok(
                ApiResult.from(
                        executor.execute(
                                GetCollectionByIdUseCase.class,
                                new GetCollectionByIdRequest(id, language)
                        ),
                        HttpStatus.OK.value()
                )
        );
    }

}
