package com.secondhand.presentationadvertapi.domain.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

public class AssignedIdOrSequenceStyleGenerator extends SequenceStyleGenerator {

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Object id = session.getEntityPersister(null, object).getClassMetadata().getIdentifier(object, session);
        Object generated = super.generate(session, object);
        if (id != generated) {
            throw new RuntimeException("Id doesn't match with generated id");
        }
        return generated;
    }
}
