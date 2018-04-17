package com.github.hippoom.ldd.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hippoom.ldd.eventhandling.EventPublisher;
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent;
import com.github.hippoom.ldd.events.AbstractTeamMemberEvent;
import com.github.hippoom.ldd.time.Clock;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

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
    @NonNull
    private SequenceGenerator sequenceGenerator;

    @Override
    public void publish(Object event) {
        AbstractTeamMemberEvent lifeConsumedEvent = (AbstractTeamMemberEvent) event;
        entityManager.persist(from(lifeConsumedEvent));
    }

    @SneakyThrows
    private TeamMemberEvent from(AbstractTeamMemberEvent event) {
        long sequence = nextSequence();
        LocalDateTime now = clock.now();
        String payload = objectMapper.writeValueAsString(event);
        return new TeamMemberEvent()
                .setSequence(sequence)
                .setVersion(event.getVersion())
                .setTeamMemberId(event.getTeamMemberId())
                .setPayload(payload)
                .setWhen(now);
    }

    private long nextSequence() {
        return sequenceGenerator.nextSequence("SEQ_TEAM_MEMBER_EVENT");
    }
}
