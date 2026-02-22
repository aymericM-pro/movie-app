package com.app.tmdb.modules.auth.request;

public record RegisterRequest(
        String email,
        String password
) {}