package com.secondhand.presentationadvertapi.domain.hibernate;

import com.secondhand.presentationadvertapi.domain.events.DomainEvent;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.Hibernate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;

@MappedSuperclass
public abstract class AggregateRoot<A extends AbstractAggregateRoot<A>> extends AbstractAggregateRoot<A> implements TransientAuditableEntity, AuditableEntity, VersionableEntity {

    @Getter
    @Transient
    protected boolean dirty = false;
    @Getter
    @Transient
    protected AggregateType aggregateType;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreationDate")
    private Instant creationDate;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "LastModifiedDate")
    private Instant lastModifiedDate;

    @Getter
    @Column(name = "IsDeleted", nullable = false)
    private boolean deleted = false;

    @Getter
    @Column(name = "Version")
    @Version
    private Long version = null;

    @Transient
    private TransientAudit transientAudit = TransientAudit.create();

    public void clearDomainEvents() {
        super.clearDomainEvents();
    }

    @Override
    public Collection<Object> domainEvents() {
        return super.domainEvents();
    }

    @Override
    public boolean equals(Object o) {
        if (isNewlyInitiated()) {
            return super.equals(o);
        }

        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }

        AggregateRoot<?> baseEntity = (AggregateRoot<?>) o;

        return new EqualsBuilder()
                .append(getId(), baseEntity.getId())
                .isEquals();
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isNewlyInitiated() {
        return getId() == null;
    }

    public <T extends DomainEvent> Collection<T> raiseEvent(Collection<T> events) {
        events.forEach(this::raiseEvent);
        return events;
    }

    public <T extends DomainEvent> void raiseEvent(T event) {
        Objects.requireNonNull(event, "event must not be null");

        if (!dirty) {
            dirty = true;
            version = version == null ? 1L : version + 1;
        }

        event.setVersion(version);
        super.registerEvent(event);
    }

    protected abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public TransientAudit getAudit() {
        return new TransientAudit(
                this.createdBy != null ? this.createdBy : transientAudit.getCreatedBy(),
                this.creationDate != null ? this.creationDate : transientAudit.getCreationDate(),
                this.modifiedBy != null ? this.modifiedBy : transientAudit.getModifiedBy(),
                this.lastModifiedDate != null ? this.lastModifiedDate : transientAudit.getLastModifiedDate()
        );
    }
}