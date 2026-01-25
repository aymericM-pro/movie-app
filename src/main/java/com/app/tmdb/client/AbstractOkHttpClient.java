package com.app.tmdb.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;

public abstract class AbstractOkHttpClient {

    protected final OkHttpClient client;
    protected final ObjectMapper mapper;

    protected AbstractOkHttpClient(OkHttpClient client, ObjectMapper mapper) {
        this.client = client;
        this.mapper = mapper;
    }

    protected <T> T get(HttpUrl url, Headers headers, Class<T> type) {
        Request request = new Request.Builder()
                .url(url)
                .headers(headers)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw mapError(response);
            }
            return mapper.readValue(response.body().byteStream(), type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract RuntimeException mapError(Response response);
}
