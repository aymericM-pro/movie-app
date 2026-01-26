package com.app.tmdb.client;

import com.app.tmdb.TmdbProperties;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class TmdbAuthInterceptor implements Interceptor {

    private final TmdbProperties properties;

    public TmdbAuthInterceptor(TmdbProperties properties) {
        this.properties = properties;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + properties.getToken())
                .addHeader("Accept", "application/json")
                .build();

        return chain.proceed(request);
    }
}