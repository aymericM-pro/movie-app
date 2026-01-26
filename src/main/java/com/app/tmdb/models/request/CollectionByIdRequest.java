package com.app.tmdb.models.request;

import lombok.Data;

@Data
public class CollectionByIdRequest extends ServiceParams {
    private Long collectionId;
    private String language = "fr-FR";

    @Override
    protected void validate() {

    }
}