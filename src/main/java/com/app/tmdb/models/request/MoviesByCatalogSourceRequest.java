package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.CatalogSourceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);
        if (type == null) {
            v.add("type is required");
        }
        if (sourceId == null) {
            v.add("sourceId is required");
        }
    }
}
