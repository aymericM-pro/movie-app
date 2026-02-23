package com.app.tmdb.modules.collections.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;

import java.util.List;

@Getter
public class GetCollectionByIdRequest extends ServiceParams {

    private final Long collectionId;
    private final String language;

    public GetCollectionByIdRequest(Long collectionId, String language) {
        this.collectionId = collectionId;
        this.language = language;
    }

    @Override
    protected void collectViolations(List<String> v) {
        checkLong(v, collectionId, "collectionId", false, true);
    }
}
