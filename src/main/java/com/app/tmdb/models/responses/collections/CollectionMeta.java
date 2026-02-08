package com.app.tmdb.models.responses.collections;

import lombok.Data;

import java.util.Map;

@Data
public class CollectionMeta {

    private Map<String, Integer> productionCompanies;
    private Map<String, Integer> productionCountries;
    private Map<String, Integer> languages;
    private Map<String, Integer> genres;
}
