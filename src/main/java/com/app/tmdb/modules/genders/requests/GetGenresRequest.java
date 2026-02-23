package com.app.tmdb.modules.genders.requests;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetGenresRequest extends PagedRequest {

    public static GetGenresRequest of(String language) {
        GetGenresRequest request = new GetGenresRequest();
        request.setLanguage(language);
        return request;
    }
}
