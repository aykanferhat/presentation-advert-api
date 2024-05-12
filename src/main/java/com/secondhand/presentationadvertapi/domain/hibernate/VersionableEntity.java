package com.secondhand.presentationadvertapi.domain.hibernate;

public interface VersionableEntity {
    Long getVersion();

    void setVersion(Long version);
}
