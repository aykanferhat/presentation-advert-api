package com.secondhand.presentationadvertapi.domain.events.category;

import com.secondhand.presentationadvertapi.domain.events.EventTypes;
import com.secondhand.presentationadvertapi.domain.events.advert.AbstractAdvertEvent;
import lombok.Getter;

@Getter
public class CategoryCreatedEvent extends AbstractCategoryEvent {

    private final String name;

    public CategoryCreatedEvent(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String getType() {
        return EventTypes.CATEGORY_CREATED_EVENT.getEventType();
    }
}
