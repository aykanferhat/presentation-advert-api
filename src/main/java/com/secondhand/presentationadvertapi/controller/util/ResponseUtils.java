package com.secondhand.presentationadvertapi.controller.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

public final class ResponseUtils {

    private ResponseUtils() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated.");
    }

    public static ResponseEntity<CreatedResponse> createdResponse(Class<?> controllerType, Long id) {
        URI uri = MvcUriComponentsBuilder.fromController(controllerType)
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).body(new CreatedResponse(id));
    }

    @Getter
    @AllArgsConstructor
    public static class CreatedResponse {

        private final Long id;
    }
}
