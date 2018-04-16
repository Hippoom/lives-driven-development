package com.github.hippoom.ldd.jpa;

import com.github.hippoom.ldd.eventhandling.TeamMemberEvent;
import com.github.hippoom.ldd.eventhandling.TeamMemberEventQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.github.hippoom.ldd.eventhandling.QTeamMemberEvent.teamMemberEvent;

@RequiredArgsConstructor
@Transactional
@Component
public class JpaTeamMemberEventQuery implements TeamMemberEventQuery {
    @NonNull
    private final EntityManager entityManager;

    @Override
    public Page<TeamMemberEvent> findBy(String openId, Pageable pageable) {
        JPAQuery<TeamMemberEvent> query = new JPAQuery<TeamMemberEvent>(entityManager)
                .from(teamMemberEvent)
                .where(teamMemberEvent.openId.eq(openId))
                .orderBy(teamMemberEvent.sequence.desc());
        return new PageImpl<>(
                query.limit(pageable.getPageSize()).offset(pageable.getOffset()).fetch(),
                pageable,
                query.fetchCount()
        );
    }
}
