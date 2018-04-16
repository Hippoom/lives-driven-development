package com.github.hippoom.ldd.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TeamMemberRepository {
    Page<TeamMember> findBy(Pageable pageable);

    long next();

    void save(TeamMember... models);

    TeamMember mustFindByOpenId(String openId);
}
