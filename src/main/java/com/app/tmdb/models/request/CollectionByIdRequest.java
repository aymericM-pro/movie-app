package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionByIdRequest extends PagedRequest{
    private Long collectionId;

    @Override
    protected void validate() {

    }
}