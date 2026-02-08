package com.app.tmdb.modules.collections.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;

@Getter
public class GetCollectionByIdRequest extends ServiceParams {

    private final Long collectionId;
    private final String language;

    public GetCollectionByIdRequest(Long collectionId, String language) {
        this.collectionId = collectionId;
        this.language = language;
    }

    @Override
    protected void validate() {
        if (collectionId == null || collectionId <= 0) {
            throw new IllegalArgumentException("collectionId must be positive");
        }
    }
}
