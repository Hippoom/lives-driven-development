package com.github.hippoom.ldd.commands

import com.github.hippoom.ldd.model.TeamMember

import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

class ConsumeMyLifeCommandFixture {

    private ConsumeMyLifeCommand target = new ConsumeMyLifeCommand()

    def with(TeamMember teamMember) {
        target.setOpenId(teamMember.getOpenId())
        this
    }

    def withReason(String value) {
        target.setReason(value)
        this
    }

    def build() {
        target
    }

    def static aConsumeMyLifeCommand() {
        new ConsumeMyLifeCommandFixture().with(tyrande().build()).withReason("I missed the stand up meeting")
    }
}
