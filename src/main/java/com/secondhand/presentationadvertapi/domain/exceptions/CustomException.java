package com.secondhand.presentationadvertapi.domain.exceptions;

import org.springframework.context.MessageSourceResolvable;

import java.util.HashMap;

public abstract class CustomException extends RuntimeException implements MessageSourceResolvable {
    private final String messageCode;
    private final Object[] messageArgs;
    private HashMap<String, String> errorParameters;

    public CustomException(String messageCode) {
        this(messageCode, new HashMap());
    }

    public CustomException(String messageCode, Object... messageArgs) {
        super(messageCode);
        this.messageCode = messageCode;
        this.messageArgs = messageArgs;
    }

    public CustomException(String messageCode, HashMap<String, String> errorParameters, Object... messageArgs) {
        super(messageCode);
        this.messageCode = messageCode;
        this.errorParameters = errorParameters;
        this.messageArgs = messageArgs;
    }

    public String getMessageCode() {
        return this.messageCode;
    }

    public Object[] getMessageArgs() {
        return this.messageArgs;
    }

    public String[] getCodes() {
        return new String[]{this.messageCode};
    }

    public Object[] getArguments() {
        return this.messageArgs;
    }

    public HashMap<String, String> getErrorParameters() {
        return this.errorParameters;
    }

    public void setErrorParameters(HashMap<String, String> errorParameters) {
        this.errorParameters = errorParameters;
    }
}
