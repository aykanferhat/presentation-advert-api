package com.secondhand.presentationadvertapi.infrastructure.controller.exception;

import com.secondhand.presentationadvertapi.infrastructure.controller.model.ErrorResponse;
import com.secondhand.presentationadvertapi.domain.exceptions.NotFoundException;
import com.secondhand.presentationadvertapi.infrastructure.service.MessageResourceService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    public GlobalControllerExceptionHandler(MessageResourceService messageResourceService) {
        this.messageResourceService = messageResourceService;
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<ErrorResponse> handleException(HttpServletRequest request, Exception ex) {
        var responseStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        LOGGER.error("Handled error, request uri: {}, status: {}", request.getRequestURI(), responseStatus, ex);
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, ex.getMessage()), responseStatus);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(HttpServletRequest request, NotFoundException ex) {
        String message = messageResourceService.getMessage(ex.getMessage(), ex.getMessageArgs());
        var responseStatus = HttpStatus.NOT_FOUND;
        LOGGER.error("Handled error, request uri: {}, status: {}", request.getRequestURI(), responseStatus, ex);
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, message), responseStatus);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException ex) {
        String error = ex.getCause().getMessage();
        var responseStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error("Handled error, request uri: {}, status: {}", request.getRequestURI(), responseStatus, ex);
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, error), responseStatus);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(HttpServletRequest request, EntityNotFoundException ex) {
        String error = ex.getMessage();
        var responseStatus = HttpStatus.NOT_FOUND;
        LOGGER.error("Handled error, request uri: {}, status: {}", request.getRequestURI(), responseStatus, ex);
        return new ResponseEntity<>(ErrorResponse.from(responseStatus, request, error), responseStatus);
    }
}