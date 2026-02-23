package com.app.tmdb.modules.collections.request;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPopularCollectionsRequest extends PagedRequest {

    public static GetPopularCollectionsRequest of(String language) {
        GetPopularCollectionsRequest request = new GetPopularCollectionsRequest();
        request.setLanguage(language);
        return request;
    }
}
