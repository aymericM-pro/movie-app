package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionByIdRequest extends PagedRequest{
    private Long collectionId;

    public static CollectionByIdRequest of(Long collectionId, String language, String region, Integer page) {
        CollectionByIdRequest request = new CollectionByIdRequest();
        request.setCollectionId(collectionId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {

    }
}