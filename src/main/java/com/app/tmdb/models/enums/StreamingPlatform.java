package com.app.tmdb.models.enums;

import lombok.Getter;

@Getter
public enum StreamingPlatform {

    NETFLIX(8),
    AMAZON_PRIME(9),
    DISNEY_PLUS(337),
    APPLE_TV_PLUS(350),
    CANAL_PLUS(381),
    OCS(531),
    HBO_MAX(384);

    private final int providerId;

    StreamingPlatform(int providerId) {
        this.providerId = providerId;
    }
}
