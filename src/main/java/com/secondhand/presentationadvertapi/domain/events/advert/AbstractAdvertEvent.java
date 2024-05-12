package com.secondhand.presentationadvertapi.domain.events.advert;

import com.secondhand.presentationadvertapi.domain.events.DomainEvent;
import com.secondhand.presentationadvertapi.domain.hibernate.AggregateType;

public abstract class AbstractAdvertEvent implements DomainEvent {
    private final long id;
    private long version;

    public AbstractAdvertEvent(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.Advert;
    }

    @Override
    public Long getAggregateId() {
        return getId();
    }
}
