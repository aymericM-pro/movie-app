package com.app.tmdb.modules.genders.requests;

import com.app.tmdb.models.request.ServiceParams;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGenresRequest extends ServiceParams {

    private String language = "fr-FR";

    @Override
    protected void validate() {
    }
}
