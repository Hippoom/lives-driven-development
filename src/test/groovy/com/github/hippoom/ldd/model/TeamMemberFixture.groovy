package com.github.hippoom.ldd.model

import static com.github.hippoom.ldd.test.Randoms.randomString

class TeamMemberFixture {
    private TeamMember target = new TeamMember()

    TeamMemberFixture withOpenId(String openId) {
        target.openId = openId
        this
    }

    def withDisplayName(String name) {
        target.displayName = name
        this
    }

    TeamMember build() {
        target
    }

    static TeamMemberFixture aTeamMember() {
        new TeamMemberFixture().withOpenId(randomString()).withDisplayName(randomString())
    }
}
