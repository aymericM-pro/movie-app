package com.app.tmdb.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI tmdbOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TMDB API")
                        .description("Proxy API vers The Movie Database")
                        .version("v1"));
    }
}
