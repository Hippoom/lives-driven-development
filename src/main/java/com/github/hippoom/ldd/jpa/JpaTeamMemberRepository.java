package com.github.hippoom.ldd.jpa;

import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static com.github.hippoom.ldd.model.QTeamMember.teamMember;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class JpaTeamMemberRepository implements TeamMemberRepository {
    @NonNull
    private final EntityManager entityManager;
    @NonNull
    private final SequenceGenerator sequenceGenerator;

    @Override
    public Page<TeamMember> findBy(Pageable pageable) {
        JPAQuery<TeamMember> query = query().from(teamMember);
        return new PageImpl<>(
                query
                        .limit(pageable.getPageSize()).offset(pageable.getOffset())
                        .orderBy(teamMember.displayName.asc())
                        .fetch(),
                pageable,
                query.fetchCount()
        );
    }

    @Override
    public long next() {
        return sequenceGenerator.nextSequence("SEQ_TEAM_MEMBER");
    }

    private JPAQuery<TeamMember> query() {
        return new JPAQuery<>(entityManager);
    }

    @Transactional
    @Override
    public void save(TeamMember... models) {
        for (TeamMember model : models) {
            entityManager.persist(model);
        }
    }

    @Override
    public TeamMember mustFindByOpenId(String openId) {
        return query().from(teamMember)
                .where(teamMember.openId.eq(openId))
                .fetchOne();
    }

    @Override
    public TeamMember mustFindBy(Long id) {
        return entityManager.find(TeamMember.class, id);
    }
}
