package com.secondhand.presentationadvertapi.infrastructure.service;

import com.secondhand.presentationadvertapi.application.service.IdGenerationService;
import com.secondhand.presentationadvertapi.infrastructure.configuration.hibernate.NativeEntityManager;
import org.springframework.stereotype.Component;

@Component
public class HibernateIdGenerationService implements IdGenerationService {
    private final NativeEntityManager nativeEntityManager;

    public HibernateIdGenerationService(NativeEntityManager nativeEntityManager) {
        this.nativeEntityManager = nativeEntityManager;
    }

    @Override
    public long generateAdvertId() {
        return Long.parseLong(String.valueOf(nativeEntityManager.getEntityManager().createNativeQuery("SELECT next_val FROM advert_id_generator").getSingleResult()));
    }

    @Override
    public long generateCategoryId() {
        return Long.parseLong(String.valueOf(nativeEntityManager.getEntityManager().createNativeQuery("SELECT next_val FROM category_id_generator").getSingleResult()));
    }
}
