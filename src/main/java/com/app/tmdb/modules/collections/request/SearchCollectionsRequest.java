package com.app.tmdb.modules.collections.request;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchCollectionsRequest extends ServiceParams {

    /**
     * Texte de recherche
     */
    private String query;

    /**
     * Langue TMDB
     */
    private String language = "fr-FR";

    @Override
    protected void collectViolations(List<String> v) {

    }
}
