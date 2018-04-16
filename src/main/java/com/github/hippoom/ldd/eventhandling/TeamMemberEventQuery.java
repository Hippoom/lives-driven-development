package com.github.hippoom.ldd.eventhandling;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamMemberEventQuery {

    Page<TeamMemberEvent> findBy(String openId, Pageable pageable);

}
