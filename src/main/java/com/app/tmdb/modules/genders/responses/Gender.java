package com.app.tmdb.modules.genders.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Gender {

    private Long id;
    private String name;
}
