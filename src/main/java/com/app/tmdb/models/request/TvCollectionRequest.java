package com.app.tmdb.models.request;

import lombok.Data;
import com.app.tmdb.models.enums.TvCollectionType;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TvCollectionRequest extends PagedRequest {

    private TvCollectionType type;
    private String language = "fr-FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        if (type == null) {
            throw new IllegalArgumentException("TvCollectionType is required");
        }
    }
}