package com.secondhand.presentationadvertapi.domain.events.advert;

import com.secondhand.presentationadvertapi.domain.events.EventTypes;
import lombok.Getter;

@Getter
public class AdvertTitleChangedEvent extends AbstractAdvertEvent {

    private final String oldTitle;

    private final String newTitle;

    public AdvertTitleChangedEvent(long id, String oldTitle, String newTitle) {
        super(id);
        this.oldTitle = oldTitle;
        this.newTitle = newTitle;
    }

    @Override
    public String getType() {
        return EventTypes.ADVERT_TITLE_CHANGED_EVENT.getEventType();
    }
}
