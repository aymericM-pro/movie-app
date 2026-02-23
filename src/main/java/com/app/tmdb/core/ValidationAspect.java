package com.app.tmdb.core;

import com.app.tmdb.errors.ValidationException;
import com.app.tmdb.models.request.ServiceParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidationAspect {

    @Around("execution(* com.app.tmdb.usecase.UseCaseExecutor.execute(..))")
    public Object validate(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        if (args.length > 1 && args[1] instanceof ServiceParams params) {
            params.validateParams();
        }

        return pjp.proceed();
    }
}