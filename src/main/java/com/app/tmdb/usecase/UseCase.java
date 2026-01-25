package com.app.tmdb.usecase;

import com.app.tmdb.requests.ServiceParams;

public abstract class UseCase<P extends ServiceParams, R> {

    public final R execute(P params) {
        params.validateParams();
        return doExecute(params);
    }

    protected abstract R doExecute(P params);
}
