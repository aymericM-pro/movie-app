package com.app.tmdb.errors;

import org.springframework.http.HttpStatus;

public interface ErrorType {
    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}