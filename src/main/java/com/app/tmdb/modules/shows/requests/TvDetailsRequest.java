package com.app.tmdb.modules.shows.requests;

import com.app.tmdb.models.request.PagedRequest;
import com.app.tmdb.models.request.ServiceParams;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TvDetailsRequest extends PagedRequest {
    private Long tvId;

    public static TvDetailsRequest of(Long tvId, String language) {
        TvDetailsRequest r = new TvDetailsRequest();
        r.setTvId(tvId);
        r.setLanguage(language);
        return r;
    }

    @Override
    protected void validate() {
        super.validate();
        checkLong(tvId, "tvId", false, true);
    }
}