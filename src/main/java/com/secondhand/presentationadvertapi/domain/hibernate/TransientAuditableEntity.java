package com.secondhand.presentationadvertapi.domain.hibernate;


public interface TransientAuditableEntity {
    TransientAudit getAudit();
}

