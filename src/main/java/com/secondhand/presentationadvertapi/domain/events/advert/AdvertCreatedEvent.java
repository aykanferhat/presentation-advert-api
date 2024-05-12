package com.secondhand.presentationadvertapi.domain.events.advert;

import com.secondhand.presentationadvertapi.domain.events.EventTypes;
import lombok.Getter;

@Getter
public class AdvertCreatedEvent extends AbstractAdvertEvent {

    private final String title;
    private final String displayName;
    private final Long categoryId;

    public AdvertCreatedEvent(long id, String title, String displayName, Long categoryId) {
        super(id);
        this.title = title;
        this.displayName = displayName;
        this.categoryId = categoryId;
    }

    @Override
    public String getType() {
        return EventTypes.ADVERT_CREATED_EVENT.getEventType();
    }
}
