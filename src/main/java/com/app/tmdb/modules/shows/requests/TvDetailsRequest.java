package com.app.tmdb.modules.shows.requests;

import com.app.tmdb.models.request.PagedRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
    protected void collectViolations(List<String> v) {
        super.collectViolations(v);
        checkLong(v, tvId, "tvId", false, true);
    }
}