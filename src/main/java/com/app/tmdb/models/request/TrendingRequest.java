package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.MediaType;
import com.app.tmdb.models.enums.TimeWindow;
import lombok.Data;

@Data
public class TrendingRequest extends ServiceParams {
    private MediaType mediaType;
    private TimeWindow timeWindow = TimeWindow.DAY;
    private String language = "fr-FR";
    private Integer page = 1;

    @Override
    protected void validate() {}
}