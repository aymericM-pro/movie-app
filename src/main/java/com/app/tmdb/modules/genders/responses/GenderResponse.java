package com.app.tmdb.modules.genders.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GenderResponse {

    private Long id;
    private String name;

    public static GenderResponse from(Gender genre) {
        return GenderResponse.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}