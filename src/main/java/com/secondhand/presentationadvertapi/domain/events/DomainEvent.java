package com.secondhand.presentationadvertapi.domain.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.secondhand.presentationadvertapi.domain.hibernate.AggregateType;

public interface DomainEvent {
    long getVersion();

    void setVersion(long version);

    @JsonIgnore
    AggregateType getAggregateType();

    @JsonIgnore
    Long getAggregateId();

    String getType();
}
