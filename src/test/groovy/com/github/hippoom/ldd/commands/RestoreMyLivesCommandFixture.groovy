package com.github.hippoom.ldd.commands

import com.github.hippoom.ldd.model.TeamMember

import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

class RestoreMyLivesCommandFixture {

    private RestoreMyLivesCommand target = new RestoreMyLivesCommand()

    def with(TeamMember teamMember) {
        target.setOpenId(teamMember.getOpenId())
        this
    }

    def withHow(String value) {
        target.setHow(value)
        this
    }

    def build() {
        target
    }

    def static aRestoreMyLivesCommand() {
        new RestoreMyLivesCommandFixture().with(tyrande().build()).withHow("I missed the stand up meeting")
    }
}
