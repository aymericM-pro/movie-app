package com.app.tmdb.modules.collections.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPopularCollectionsRequest extends ServiceParams {

    /**
     * Langue TMDB (ex: fr-FR, en-US)
     */
    private String language = "fr-FR";

    @Override
    protected void validate() {

    }
}
