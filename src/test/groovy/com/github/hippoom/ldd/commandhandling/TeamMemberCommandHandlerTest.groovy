package com.github.hippoom.ldd.commandhandling

import com.github.hippoom.ldd.commands.ConsumeMyLifeCommand
import com.github.hippoom.ldd.eventhandling.EventPublisher
import com.github.hippoom.ldd.events.TeamMemberLifeConsumedEvent
import com.github.hippoom.ldd.events.TeamMemberLivesRestoredEvent
import com.github.hippoom.ldd.model.NotEnoughLivesException
import com.github.hippoom.ldd.model.TeamMemberRepository
import spock.lang.Specification

import static com.github.hippoom.ldd.commands.ConsumeMyLifeCommandFixture.aConsumeMyLifeCommand
import static com.github.hippoom.ldd.commands.RestoreMyLivesCommandFixture.aRestoreMyLivesCommand
import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan
import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

class TeamMemberCommandHandlerTest extends Specification {

    def teamMemberRepository = Mock(TeamMemberRepository)
    def eventPublisher = Mock(EventPublisher)
    def subject = new TeamMemberCommandHandler(teamMemberRepository, eventPublisher)

    def "it should handle ConsumeMyLifeCommand"() {
        given:
        def me = tyrande().build()
        def command = aConsumeMyLifeCommand().with(me).build()
        teamMemberRepository.mustFindByOpenId(command.openId) >> me

        and:
        def before = me.remainingLives

        when:
        def after = subject.handle(command)

        then: "1 life is consumed"
        assert after.remainingLives == before - 1

        and:
        1 * eventPublisher.publish(new TeamMemberLifeConsumedEvent(me.getId(), me.getVersion(), command.getWhy()))
    }

    def "it should throw exception when handling ConsumeMyLifeCommand given not enough lives"() {
        given:
        def me = illidan().build()
        def command = new ConsumeMyLifeCommand()
        command.setOpenId(me.getOpenId())
        teamMemberRepository.mustFindByOpenId(command.openId) >> me

        when:
        subject.handle(command)

        then:
        thrown(NotEnoughLivesException)
    }

    def "it should handle RestoreMyLivesCommand"() {
        given:
        def me = illidan().build()
        def command = aRestoreMyLivesCommand().with(me).build()
        teamMemberRepository.mustFindByOpenId(command.openId) >> me

        when:
        def after = subject.handle(command)

        then: "3 lives is restored"
        assert after.remainingLives == 3
        1 * eventPublisher.publish(new TeamMemberLivesRestoredEvent(me.getId(), me.getVersion(), command.getHow()))
    }
}
