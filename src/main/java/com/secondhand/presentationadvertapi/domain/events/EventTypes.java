package com.secondhand.presentationadvertapi.domain.events;

import lombok.Getter;

@Getter
public enum EventTypes {
    ADVERT_CREATED_EVENT("advert.created"),


    CATEGORY_CREATED_EVENT("category.created");

    private final String eventType;


    EventTypes(String eventType) {
        this.eventType = eventType;
    }
}
