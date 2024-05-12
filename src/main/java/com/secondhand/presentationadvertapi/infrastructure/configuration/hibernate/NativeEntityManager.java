package com.secondhand.presentationadvertapi.infrastructure.configuration.hibernate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class NativeEntityManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(NativeEntityManager.class);
    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    private final ObjectMapper objectMapper;
    private final EntityManager entityManager;

    public NativeEntityManager(ObjectMapper objectMapper, EntityManager entityManager) {
        this.objectMapper = objectMapper;
        this.entityManager = entityManager;
    }

    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(String.class);
        ret.add(Void.class);
        return ret;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public <T> List<T> query(String sql, Consumer<NativeQuery> modifier, Class<T> targetType) {
        Session session = entityManager.unwrap(Session.class);
        var nativeQuery = session.createNativeQuery(sql);
        modifier.accept(nativeQuery);
        @SuppressWarnings("deprecation") var mapResult = nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();


        if (isWrapperType(targetType)) {
            Object primitiveList = mapResult.stream()
                    .map(it -> (((HashMap<String, Object>) it).values().stream().map(Optional::ofNullable).findFirst().flatMap(Function.identity()).orElse(null)))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            var javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, targetType);
            var result = objectMapper.convertValue(primitiveList, javaType);
            return (List<T>) result;
        }

        var javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, targetType);
        return objectMapper.convertValue(mapResult, javaType);

    }

    public <T> T firstOrDefault(String sql, Consumer<NativeQuery> modifier, Class<T> type) {
        List<T> list = this.query(sql, modifier, type);
        if (!list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public <T> List<T> queryAsJson(String sql, Consumer<NativeQuery> modifier, Class<T> type) {
        List<String> resultList = query(sql, modifier, String.class);
        if (!resultList.isEmpty()) {
            return List.of();
        }

        var json = String.join("", resultList);
        try {
            CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, type);
            return objectMapper.readValue(json, javaType);
        } catch (JsonProcessingException e) {
            LOGGER.error("Unknown error occurred while parsing json={}", json, e);
            throw new RuntimeException("unknown.error");
        }
    }
}