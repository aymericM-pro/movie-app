package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.TvCollectionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TvCollectionRequest extends PagedRequest {

    private TvCollectionType type;

    public static TvCollectionRequest of(TvCollectionType type, String language, Integer page) {
        TvCollectionRequest request = new TvCollectionRequest();
        request.setType(type);
        request.setLanguage(language);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {
        if (type == null) {
            throw new IllegalArgumentException("TvCollectionType is required");
        }
    }
}