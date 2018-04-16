package com.github.hippoom.ldd.jpa;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class SequenceGenerator {
    @NonNull
    private final EntityManager entityManager;

    public long nextSequence(String sequenceName) {
        Query query = entityManager.createNativeQuery("SELECT nextval(?1)");
        query.setParameter(1, sequenceName);
        return ((Number) query.getSingleResult()).longValue();
    }
}
