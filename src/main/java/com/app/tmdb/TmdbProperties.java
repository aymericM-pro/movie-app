package com.app.tmdb;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "tmdb")
public class TmdbProperties {

    /**
     * Base URL TMDB (ex: https://api.themoviedb.org/3)
     */
    private String baseUrl;

    /**
     * Account ID TMDB
     */
    private String accountId;

    /**
     * Bearer token TMDB
     */
    private String token;
}
