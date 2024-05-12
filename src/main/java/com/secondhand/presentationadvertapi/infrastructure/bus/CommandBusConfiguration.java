package com.secondhand.presentationadvertapi.infrastructure.bus;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.secondhand.presentationadvertapi.application.bus.Command;
import com.secondhand.presentationadvertapi.application.bus.ValidationMiddleware;
import jakarta.validation.Validation;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommandBusConfiguration {

    @Bean
    Pipeline pipeline(ObjectProvider<Command.Handler> commandHandlers, ObjectProvider<Command.Middleware> middlewares) {
        return new Pipelinr()
                .with(commandHandlers::stream)
                .with(middlewares::stream);
    }

    @Bean
    ValidationMiddleware validationMiddleware() {
        return new ValidationMiddleware(Validation.buildDefaultValidatorFactory().getValidator());
    }
}