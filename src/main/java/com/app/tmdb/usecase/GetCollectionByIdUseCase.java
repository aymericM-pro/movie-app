package com.app.tmdb.usecase;

import com.app.tmdb.client.TmdbClient;
import com.app.tmdb.models.request.CollectionByIdRequest;
import com.app.tmdb.models.responses.CollectionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GetCollectionByIdUseCase
        extends UseCase<CollectionByIdRequest, CollectionResponse> {

    private final TmdbClient client;

    @Override
    protected CollectionResponse doExecute(CollectionByIdRequest request) {
        return client.getCollectionById(request.getCollectionId(), request.getLanguage());
    }
}
