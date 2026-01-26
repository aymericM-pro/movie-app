package com.app.tmdb.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionResponse {

    private Long id;
    private String name;
    private String overview;
    private String posterPath;
    private String backdropPath;

    private List<CollectionMovie> parts;

}
