package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagedRequest extends ServiceParams {
    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        checkString(language, "language", true, null, null);
        checkString(region, "region", true, null, null);
        checkInt(page, "page", true, 1, 1000);
    }
}
