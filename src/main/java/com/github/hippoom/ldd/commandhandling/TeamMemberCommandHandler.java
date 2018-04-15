package com.github.hippoom.ldd.commandhandling;

import com.github.hippoom.ldd.commands.ConsumeMyLifeCommand;
import com.github.hippoom.ldd.model.TeamMember;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class TeamMemberCommandHandler {

    public TeamMember handle(ConsumeMyLifeCommand command) {
        return null;
    }
}
