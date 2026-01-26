package com.app.tmdb.config;

import com.app.tmdb.TmdbProperties;
import com.app.tmdb.client.TmdbAuthInterceptor;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OkHttpConfig {

    @Bean
    public TmdbAuthInterceptor tmdbAuthInterceptor(TmdbProperties properties) {
        return new TmdbAuthInterceptor(properties);
    }

    @Bean
    public OkHttpClient okHttpClient(TmdbAuthInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
