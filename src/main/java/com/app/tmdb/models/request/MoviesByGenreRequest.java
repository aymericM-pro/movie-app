package com.app.tmdb.models.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MoviesByGenreRequest extends ServiceParams {

    private Integer genreId;
    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        if (genreId == null) {
            throw new IllegalArgumentException("genre is required");
        }
    }
}
