package com.app.tmdb.errors;

import org.springframework.http.HttpStatus;

public enum MovieListError implements ErrorType {

    LIST_NOT_FOUND("LIST_NOT_FOUND", HttpStatus.NOT_FOUND, "Liste introuvable"),
    MOVIE_ALREADY_IN_LIST("MOVIE_ALREADY_IN_LIST", HttpStatus.CONFLICT, "Film déjà présent dans la liste"),
    MOVIE_NOT_IN_LIST("MOVIE_NOT_IN_LIST", HttpStatus.NOT_FOUND, "Film non présent dans la liste");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    MovieListError(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
