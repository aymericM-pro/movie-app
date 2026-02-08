package com.app.tmdb.modules.collections.usecases;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.responses.CollectionResponse;
import com.app.tmdb.modules.collections.request.GetCollectionByIdRequest;
import com.app.tmdb.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetCollectionByIdUseCase extends UseCase<GetCollectionByIdRequest, CollectionResponse> {

    private final TmdbClient tmdbClient;

    @Override
    protected CollectionResponse doExecute(GetCollectionByIdRequest params) {
        return tmdbClient.getCollectionById(
                params.getCollectionId(),
                params.getLanguage()
        );
    }
}
