package com.app.tmdb.modules.auth.request;

public record LoginRequest(
        String email,
        String password
) {}