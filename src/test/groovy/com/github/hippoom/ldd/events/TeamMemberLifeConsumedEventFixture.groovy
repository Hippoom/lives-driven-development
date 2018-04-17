package com.github.hippoom.ldd.events

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent
import com.github.hippoom.ldd.model.TeamMember
import com.github.hippoom.ldd.test.Randoms

import java.time.LocalDateTime

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember
import static java.time.LocalDateTime.now

class TeamMemberLifeConsumedEventFixture {
    private Long teamMemberId
    private int version
    private String why
    private Long sequence
    private LocalDateTime when

    def withSequence(long sequence) {
        this.sequence = sequence
        this
    }

    def with(TeamMember member) {
        this.teamMemberId = member.getId()
        this.version = member.version
        this
    }

    def withWhy(String why) {
        this.why = why
        this
    }

    def when(LocalDateTime when) {
        this.when = when
        this
    }

    def but() {
        this
    }

    TeamMemberLifeConsumedEvent build() {
        new TeamMemberLifeConsumedEvent(teamMemberId, version, why)
    }

    TeamMemberEvent buildEvent() {
        def event = build()
        return new TeamMemberEvent()
                .setSequence(sequence)
                .setVersion(event.getVersion())
                .setTeamMemberId(event.getTeamMemberId())
                .setPayload(new ObjectMapper().writeValueAsString(event))
                .setWhen(when)
    }

    static TeamMemberLifeConsumedEventFixture aLifeConsumedEvent() {
        new TeamMemberLifeConsumedEventFixture()
                .withSequence(Randoms.nextLong())
                .with(aTeamMember().build())
                .withWhy("I missed the stand up meeting")
                .when(now())
    }
}
