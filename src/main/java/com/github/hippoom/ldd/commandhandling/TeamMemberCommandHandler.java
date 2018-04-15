package com.github.hippoom.ldd.commandhandling;

import com.github.hippoom.ldd.commands.ConsumeMyLifeCommand;
import com.github.hippoom.ldd.commands.RestoreMyLivesCommand;
import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Component
public class TeamMemberCommandHandler {

    @NonNull
    private TeamMemberRepository teamMemberRepository;

    public TeamMember handle(ConsumeMyLifeCommand command) {
        TeamMember me = teamMemberRepository.mustFindBy(command.getOpenId());
        me.consumeLife();
        return me;
    }

    public TeamMember handle(RestoreMyLivesCommand command) {
        TeamMember me = teamMemberRepository.mustFindBy(command.getOpenId());
        me.restoreLives();
        return me;
    }
}
