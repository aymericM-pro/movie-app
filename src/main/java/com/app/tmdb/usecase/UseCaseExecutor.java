package com.app.tmdb.usecase;

import com.app.tmdb.models.request.ServiceParams;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class UseCaseExecutor {

    private final ApplicationContext context;

    public UseCaseExecutor(ApplicationContext context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public <P extends ServiceParams, R> R execute(Class<? extends UseCase<P, R>> useCaseClass, P params) {
        UseCase<P, R> useCase = context.getBean(useCaseClass);
        return useCase.execute(params);
    }
}
