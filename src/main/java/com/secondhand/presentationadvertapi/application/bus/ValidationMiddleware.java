package com.secondhand.presentationadvertapi.application.bus;

import an.awesome.pipelinr.Command.Middleware;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.Set;

public class ValidationMiddleware implements Middleware {
    private final Validator validator;

    public ValidationMiddleware(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <R, C extends an.awesome.pipelinr.Command<R>> R invoke(C command, Next<R> next) {
        Set<ConstraintViolation<C>> constraintViolations = validator.validate(command);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return next.invoke();
    }
}
