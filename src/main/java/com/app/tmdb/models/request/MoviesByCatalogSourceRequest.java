package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.CatalogSourceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoviesByCatalogSourceRequest extends PagedRequest {

    private CatalogSourceType type;
    private Integer sourceId;

    public static MoviesByCatalogSourceRequest of(CatalogSourceType type, Integer sourceId, String language, String region, Integer page) {
        MoviesByCatalogSourceRequest request = new MoviesByCatalogSourceRequest();
        request.setType(type);
        request.setSourceId(sourceId);
        request.setLanguage(language);
        request.setRegion(region);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {
        if (type == null) {
            throw new IllegalArgumentException("type is required");
        }
        if (sourceId == null) {
            throw new IllegalArgumentException("sourceId is required");
        }
    }
}
