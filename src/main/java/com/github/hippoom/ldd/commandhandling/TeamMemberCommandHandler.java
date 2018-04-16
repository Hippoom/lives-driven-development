package com.github.hippoom.ldd.commandhandling;

import com.github.hippoom.ldd.commands.ConsumeMyLifeCommand;
import com.github.hippoom.ldd.commands.RestoreMyLivesCommand;
import com.github.hippoom.ldd.eventhandling.EventPublisher;
import com.github.hippoom.ldd.events.TeamMemberLifeConsumedEvent;
import com.github.hippoom.ldd.events.TeamMemberLivesRestoredEvent;
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

    @NonNull
    private EventPublisher eventPublisher;

    public TeamMember handle(ConsumeMyLifeCommand command) {
        TeamMember me = teamMemberRepository.mustFindByOpenId(command.getOpenId());
        me.consumeLife();
        eventPublisher.publish(new TeamMemberLifeConsumedEvent(command.getOpenId(), me.getVersion(), command.getWhy()));
        return me;
    }

    public TeamMember handle(RestoreMyLivesCommand command) {
        TeamMember me = teamMemberRepository.mustFindByOpenId(command.getOpenId());
        me.restoreLives();
        eventPublisher.publish(new TeamMemberLivesRestoredEvent(command.getOpenId(), me.getVersion(), command.getHow()));
        return me;
    }
}
