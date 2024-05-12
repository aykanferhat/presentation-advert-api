package com.secondhand.presentationadvertapi.infrastructure.configuration.hibernate;

import com.secondhand.presentationadvertapi.domain.hibernate.AuditableEntity;
import com.secondhand.presentationadvertapi.domain.hibernate.TransientAuditableEntity;
import com.secondhand.presentationadvertapi.domain.hibernate.VersionableEntity;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.*;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;


@Component
public class PreAuditEventListener implements PreInsertEventListener, PreUpdateEventListener {

    private final EntityManagerFactory entityManagerFactory;

    public PreAuditEventListener(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    private static <T> T as(Object o, Class<T> tClass) {
        return tClass.isInstance(o) ? (T) o : null;
    }

    @Override
    public boolean onPreInsert(PreInsertEvent event) {
        var versionableEntity = as(event.getEntity(), VersionableEntity.class);
        if (versionableEntity == null) {
            return false;
        }

        var transientAuditableEntity = as(event.getEntity(), TransientAuditableEntity.class);
        if (transientAuditableEntity == null) {
            return false;
        }

        var auditableEntity = as(event.getEntity(), AuditableEntity.class);
        if (auditableEntity == null) {
            return false;
        }

        var audit = transientAuditableEntity.getAudit();
        if (audit == null) {
            return false;
        }

        auditableEntity.setCreatedBy(audit.getCreatedBy());
        auditableEntity.setCreationDate(audit.getCreationDate());
        auditableEntity.setModifiedBy(audit.getModifiedBy());
        auditableEntity.setLastModifiedDate(audit.getLastModifiedDate());

        String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
        Object[] state = event.getState();
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyNames[i].equals("version")) {
                state[i] = Math.max(0, versionableEntity.getVersion());
                continue;
            }

            if (propertyNames[i].equals("createdBy")) {
                state[i] = audit.getCreatedBy();
                continue;
            }

            if (propertyNames[i].equals("creationDate")) {
                state[i] = audit.getCreationDate();
                continue;
            }

            if (propertyNames[i].equals("lastModifiedDate")) {
                state[i] = audit.getLastModifiedDate();
                continue;
            }

            if (propertyNames[i].equals("modifiedBy")) {
                state[i] = audit.getModifiedBy();
                continue;
            }
        }
        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent event) {
        var versionableEntity = as(event.getEntity(), VersionableEntity.class);
        if (versionableEntity == null) {
            return false;
        }

        var transientAuditableEntity = as(event.getEntity(), TransientAuditableEntity.class);
        if (transientAuditableEntity == null) {
            return false;
        }

        var auditableEntity = as(event.getEntity(), AuditableEntity.class);
        if (auditableEntity == null) {
            return false;
        }

        var audit = transientAuditableEntity.getAudit();
        if (audit == null) {
            return false;
        }

        auditableEntity.setModifiedBy(audit.getModifiedBy());
        auditableEntity.setLastModifiedDate(audit.getLastModifiedDate());

        String[] propertyNames = event.getPersister().getEntityMetamodel().getPropertyNames();
        Object[] state = event.getState();
        Object[] oldState = event.getOldState();
        for (int i = 0; i < propertyNames.length; i++) {
            if (propertyNames[i].equals("version")) {
                state[i] = Math.max((Long) oldState[i], (Long) state[i]);
                continue;
            }

            if (propertyNames[i].equals("lastModifiedDate")) {
                state[i] = audit.getLastModifiedDate();
                continue;
            }

            if (propertyNames[i].equals("modifiedBy")) {
                state[i] = audit.getModifiedBy();
            }
        }

        return false;
    }

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.PRE_INSERT).appendListener(this);
        registry.getEventListenerGroup(EventType.PRE_UPDATE).appendListener(this);
    }
}
