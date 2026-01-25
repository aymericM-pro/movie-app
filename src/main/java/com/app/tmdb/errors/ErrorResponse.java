package com.app.tmdb.errors;

public record ErrorResponse(
        String code,
        String message) {
}
