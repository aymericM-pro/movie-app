package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.CatalogSourceType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoviesByCatalogSourceRequest extends ServiceParams {

    private CatalogSourceType type;
    private Integer sourceId;

    private String language = "en-US";
    private String region = "FR";
    private Integer page = 1;

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
