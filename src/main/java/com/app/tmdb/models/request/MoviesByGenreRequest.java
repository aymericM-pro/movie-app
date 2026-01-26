package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.MovieGenre;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MoviesByGenreRequest extends ServiceParams {

    private MovieGenre genre;
    private String language = "fr-FR";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        if (genre == null) {
            throw new IllegalArgumentException("genre is required");
        }
    }
}
