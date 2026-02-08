package com.app.tmdb.modules.movies.requests;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopRatedMoviesRequest extends ServiceParams {

    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        if (page == null || page < 1) {
            page = 1;
        }
        if (language == null || language.isBlank()) {
            language = "fr-FR";
        }
        if (region == null || region.isBlank()) {
            region = "FR";
        }
    }
}
