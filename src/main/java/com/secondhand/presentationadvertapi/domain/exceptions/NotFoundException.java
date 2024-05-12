package com.secondhand.presentationadvertapi.domain.exceptions;

public class NotFoundException extends CustomException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String messageCode, Object... messageArgs) {
        super(messageCode, messageArgs);
    }
}
