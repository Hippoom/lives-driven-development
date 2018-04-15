package com.github.hippoom.ldd.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamMemberRepository {
    Page<TeamMember> findBy(Pageable pageable);

    void save(TeamMember... models);
}
