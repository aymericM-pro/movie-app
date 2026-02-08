package com.app.tmdb.modules.collections.responses;

import com.app.tmdb.models.responses.CollectionResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PopularCollectionResponse {

    private Long id;
    private String name;
    private String overview;
    private String posterPath;
    private String backdropPath;
    private Integer partsCount;

    public static PopularCollectionResponse from(CollectionResponse c) {
        return PopularCollectionResponse.builder()
                .id(c.getId())
                .name(c.getName())
                .overview(c.getOverview())
                .posterPath(c.getPosterPath())
                .backdropPath(c.getBackdropPath())
                .partsCount(0)
                .build();
    }
}
