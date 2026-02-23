package com.app.tmdb.models.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagedRequest extends ServiceParams {
    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void collectViolations(List<String> v) {
        checkString(v, language, "language", true, null, null);
        checkString(v, region, "region", true, null, null);
        checkInt(v, page, "page", true, 1, 1000);
    }

}
