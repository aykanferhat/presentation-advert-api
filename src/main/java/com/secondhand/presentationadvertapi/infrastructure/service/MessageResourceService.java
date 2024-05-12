package com.secondhand.presentationadvertapi.infrastructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageResourceService {

    private final MessageSource messageSource;

    @Autowired
    public MessageResourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(MessageSourceResolvable resolvable) {
        return messageSource.getMessage(resolvable, getLocale());
    }

    public String getMessage(String key, Object... args) {
        return messageSource.getMessage(key, args, getLocale());
    }

    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }
}
