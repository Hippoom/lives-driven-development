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

import java.util.Optional;

import static com.github.hippoom.ldd.model.QTeamMember.teamMember;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Component
public class JpaTeamMemberRepository implements TeamMemberRepository {
    @NonNull
    private final EntityManager entityManager;

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
    public TeamMember mustFindBy(String openId) {
        return null;
    }
}
