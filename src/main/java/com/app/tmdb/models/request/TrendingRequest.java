package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.MediaType;
import com.app.tmdb.models.enums.TimeWindow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrendingRequest extends PagedRequest {
    private MediaType mediaType;
    private TimeWindow timeWindow = TimeWindow.DAY;

    @Override
    protected void validate() {
        super.validate();
        if (mediaType == null) fail("mediaType is required");
    }
}