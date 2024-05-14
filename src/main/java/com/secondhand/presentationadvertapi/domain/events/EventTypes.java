package com.secondhand.presentationadvertapi.domain.events;

import lombok.Getter;

@Getter
public enum EventTypes {

    ADVERT_CREATED_EVENT("advert.created"),

    ADVERT_TITLE_CHANGED_EVENT("advert.title-changed"),

    CATEGORY_CREATED_EVENT("category.created");

    private final String eventType;


    EventTypes(String eventType) {
        this.eventType = eventType;
    }
}
