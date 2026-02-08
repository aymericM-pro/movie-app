package com.app.tmdb.modules.collections.mapper;

import com.app.tmdb.models.responses.CollectionResponse;
import com.app.tmdb.modules.collections.responses.CollectionSearchItemResponse;
import com.app.tmdb.modules.collections.responses.PopularCollectionResponse;

public final class CollectionMapper {

    private CollectionMapper() {
    }

    /**
     * Mapping depuis /collection/{id}
     * → on peut fallback sur les parts
     */
    public static PopularCollectionResponse from(CollectionResponse c) {

        String poster = c.getPosterPath();
        String backdrop = c.getBackdropPath();

        if ((poster == null || backdrop == null)
                && c.getParts() != null
                && !c.getParts().isEmpty()) {

            var first = c.getParts().get(0);

            if (poster == null) {
                poster = first.getPosterPath();
            }

            if (backdrop == null) {
                backdrop = first.getBackdropPath();
            }
        }

        return PopularCollectionResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .overview(c.getOverview())
                .posterPath(poster)
                .backdropPath(backdrop)
                .partsCount(c.getParts() != null ? c.getParts().size() : 0)
                .build();
    }

    /**
     * Mapping depuis /search/collection
     * → pas de parts, pas de fallback possible
     */
    public static PopularCollectionResponse fromSearch(
            CollectionSearchItemResponse c
    ) {
        return PopularCollectionResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .overview(c.getOverview())
                .posterPath(c.getPosterPath())
                .backdropPath(c.getBackdropPath())
                .partsCount(null) // inconnu à ce stade
                .build();
    }
}
