package com.github.hippoom.ldd.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hippoom.ldd.eventhandling.EventPublisher;
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent;
import com.github.hippoom.ldd.events.TeamMemberLifeConsumedEvent;
import com.github.hippoom.ldd.time.Clock;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Transactional
@Component
public class JpaEventPublisher implements EventPublisher {
    @NonNull
    private final EntityManager entityManager;
    @NonNull
    private ObjectMapper objectMapper;
    @NonNull
    private Clock clock;

    @Override
    public void publish(Object event) {
        TeamMemberLifeConsumedEvent lifeConsumedEvent = (TeamMemberLifeConsumedEvent) event;
        entityManager.persist(from(lifeConsumedEvent));
    }

    @SneakyThrows
    private TeamMemberEvent from(TeamMemberLifeConsumedEvent event) {
        Query query = entityManager.createNativeQuery("SELECT nextval('SEQ_TEAM_MEMBER_EVENT')");
        return new TeamMemberEvent()
                .setSequence(((Number) query.getSingleResult()).longValue())
                .setVersion(event.getVersion())
                .setOpenId(event.getOpenId())
                .setPayload(objectMapper.writeValueAsString(event))
                .setWhen(clock.now());
    }
}
