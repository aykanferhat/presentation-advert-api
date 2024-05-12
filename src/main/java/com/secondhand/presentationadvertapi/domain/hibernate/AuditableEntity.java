package com.secondhand.presentationadvertapi.domain.hibernate;

import java.time.Instant;

public interface AuditableEntity {
    void setCreatedBy(String createdBy);

    void setCreationDate(Instant creationDate);

    void setLastModifiedDate(Instant lastModifiedDate);

    void setModifiedBy(String modifiedBy);
}
