package com.secondhand.presentationadvertapi.domain.hibernate;

import org.slf4j.MDC;

import java.time.Instant;

public class TransientAudit {
    private String createdBy;
    private Instant creationDate;
    private String modifiedBy;
    private Instant lastModifiedDate;

    public TransientAudit(String createdBy, Instant creationDate, String modifiedBy, Instant lastModifiedDate) {
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifiedBy = modifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static TransientAudit create() {
        var email = getUserEmailOrDefault();
        var now = Instant.now();
        return new TransientAudit(email, now, email, now);
    }

    private static String getUserEmailOrDefault() {
        var email = MDC.get("X-UserEmail") == null ? MDC.get("x-useremail") : MDC.get("X-UserEmail");
        if (email == null)
            return "anonymous";
        return email;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }
}
