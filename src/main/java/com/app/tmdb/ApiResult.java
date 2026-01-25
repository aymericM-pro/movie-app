package com.app.tmdb;

import lombok.Getter;

@Getter
public class ApiResult <T> {
    private final T data;
    private final int status;

    private ApiResult(T data, int status) {
        this.data = data;
        this.status = status;
    }

    public static <T> ApiResult<T> from(T data, int status) {
        return new ApiResult<>(data, status);
    }

}
