package com.secondhand.presentationadvertapi.domain.events.category;

import com.secondhand.presentationadvertapi.domain.events.DomainEvent;
import com.secondhand.presentationadvertapi.domain.hibernate.AggregateType;
import lombok.Getter;

@Getter
public abstract class AbstractCategoryEvent implements DomainEvent {
    private final long id;
    private long version;

    public AbstractCategoryEvent(long id) {
        this.id = id;
    }


    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public AggregateType getAggregateType() {
        return AggregateType.Category;
    }

    @Override
    public Long getAggregateId() {
        return getId();
    }
}
