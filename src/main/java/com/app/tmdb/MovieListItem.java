package com.app.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MovieListItem {

    private long id;
    private String name;
    private String description;

    @JsonProperty("favorite_count")
    private int favoriteCount;

    @JsonProperty("item_count")
    private int itemCount;

    @JsonProperty("iso_639_1")
    private String iso6391;

    @JsonProperty("iso_3166_1")
    private String iso31661;

    @JsonProperty("list_type")
    private String listType;

    @JsonProperty("poster_path")
    private String posterPath;
}
