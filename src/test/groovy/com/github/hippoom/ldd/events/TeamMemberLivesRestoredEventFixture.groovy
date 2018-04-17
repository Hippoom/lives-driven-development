package com.github.hippoom.ldd.events

import com.fasterxml.jackson.databind.ObjectMapper
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent
import com.github.hippoom.ldd.model.TeamMember
import com.github.hippoom.ldd.test.Randoms

import java.time.LocalDateTime

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember
import static java.time.LocalDateTime.now

class TeamMemberLivesRestoredEventFixture {
    private Long teamMemberId
    private int version
    private String how
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

    def withHow(String how) {
        this.how = how
        this
    }

    def when(LocalDateTime when) {
        this.when = when
        this
    }

    def but() {
        this
    }

    TeamMemberLivesRestoredEvent build() {
        new TeamMemberLivesRestoredEvent(teamMemberId, version, how)
    }

    TeamMemberEvent buildEvent() {
        def event = build()
        return new TeamMemberEvent()
                .setSequence(sequence)
                .setVersion(event.getVersion())
                .setType(event.getType())
                .setTeamMemberId(event.getTeamMemberId())
                .setPayload(new ObjectMapper().writeValueAsString(event))
                .setWhen(when)
    }

    static TeamMemberLivesRestoredEventFixture aLivesRestoredEvent() {
        new TeamMemberLivesRestoredEventFixture()
                .withSequence(Randoms.nextLong())
                .with(aTeamMember().build())
                .withHow("I bought milk tea for the team on April 1st")
                .when(now())
    }
}
