package com.app.tmdb.models.request;

import com.app.tmdb.models.enums.StreamingPlatform;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MoviesByPlatformRequest extends ServiceParams  {

    private StreamingPlatform platform;
    private String language = "en-US";
    private String region = "FR";
    private Integer page = 1;

    @Override
    protected void validate() {
        if (platform == null) {
            throw new IllegalArgumentException("platform is required");
        }
    }

}