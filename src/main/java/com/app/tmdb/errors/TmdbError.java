package com.app.tmdb.errors;

import org.springframework.http.HttpStatus;

public enum TmdbError implements ErrorType {

    MOVIE_NOT_FOUND("TMDB_MOVIE_NOT_FOUND", HttpStatus.NOT_FOUND, "Film TMDB introuvable"),
    UNAUTHORIZED("TMDB_UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "Token TMDB invalide ou manquant"),
    RATE_LIMIT_EXCEEDED("TMDB_RATE_LIMIT", HttpStatus.TOO_MANY_REQUESTS, "Trop de requêtes vers TMDB"),
    TMDB_INTERNAL_ERROR("TMDB_INTERNAL_ERROR", HttpStatus.BAD_GATEWAY, "Erreur interne TMDB");

    private final String code;
    private final HttpStatus httpStatus;
    private final String message;

    TmdbError(String code, HttpStatus httpStatus, String message) {
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
