package com.github.hippoom.ldd.jpa;

import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class JpaTeamMemberRepository implements TeamMemberRepository {
    @Override
    public Page<TeamMember> findBy(Pageable pageable) {
        return new PageImpl<>(emptyList());
    }
}
