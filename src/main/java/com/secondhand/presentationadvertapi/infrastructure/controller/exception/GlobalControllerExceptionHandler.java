package com.secondhand.presentationadvertapi.infrastructure.controller.exception;

import com.secondhand.presentationadvertapi.infrastructure.controller.model.ErrorResponse;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import com.secondhand.presentationadvertapi.infrastructure.service.MessageResourceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private final MessageResourceService messageResourceService;

    public GlobalControllerExceptionHandler(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        String message = messageResourceService.getMessage(ex.getMessage(), ex.getMessageArgs());
        var responseStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, message), responseStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        String error = ex.getCause().getMessage();
        var responseStatus = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, error), responseStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        String error = ex.getMessage();
        var responseStatus = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, error), responseStatus);
    }
}