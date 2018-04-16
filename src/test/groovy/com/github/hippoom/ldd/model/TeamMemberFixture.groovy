package com.github.hippoom.ldd.model

import static com.github.hippoom.ldd.test.Randoms.nextLong
import static com.github.hippoom.ldd.test.Randoms.randomString

class TeamMemberFixture {
    private TeamMember target = new TeamMember()

    TeamMemberFixture withId(long id) {
        target.setId(id)
        this
    }

    TeamMemberFixture withOpenId(String openId) {
        target.openId = openId
        this
    }

    def withDisplayName(String name) {
        target.displayName = name
        this
    }

    def withRemainingLives(int value) {
        target.remainingLives = value
        this
    }

    def but() {
        this
    }

    TeamMember build() {
        target
    }

    static TeamMemberFixture aTeamMember() {
        new TeamMemberFixture()
                .withId(nextLong())
                .withOpenId(randomString())
                .withDisplayName(randomString())
                .withRemainingLives(3)
    }

    static TeamMemberFixture tyrande() {
        new TeamMemberFixture()
                .withId(1)
                .withOpenId("Tyrande Whisperwind")
                .withDisplayName("Tyrande Whisperwind")
                .withRemainingLives(3)
    }

    static TeamMemberFixture illidan() {
        new TeamMemberFixture()
                .withId(3)
                .withOpenId("Illidan Stormrage")
                .withDisplayName("Illidan Stormrage")
                .withRemainingLives(0)
    }
}
