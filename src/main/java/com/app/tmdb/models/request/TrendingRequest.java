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

    public static TrendingRequest of(MediaType mediaType, TimeWindow timeWindow, String language, Integer page) {
        TrendingRequest request = new TrendingRequest();
        request.setMediaType(mediaType);
        request.setTimeWindow(timeWindow);
        request.setLanguage(language);
        request.setPage(page);
        return request;
    }

    @Override
    protected void validate() {
        super.validate();
        if (mediaType == null) fail("mediaType is required");
    }
}