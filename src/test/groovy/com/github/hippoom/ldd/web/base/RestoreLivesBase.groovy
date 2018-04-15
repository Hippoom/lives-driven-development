package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.commands.RestoreMyLivesCommand
import com.github.hippoom.ldd.model.NotEnoughLivesException
import com.github.hippoom.ldd.model.TeamMember
import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithIllidanStormrage

import static com.github.hippoom.ldd.commands.ConsumeMyLifeCommandFixture.aConsumeMyLifeCommand
import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan

@WithIllidanStormrage
abstract class RestoreLivesBase extends AbstractWebMvcTest {

    def setup() {
        def me = illidan().but().withRemainingLives(3).build()
        teamMemberCommandHandler.handle(restoreMyLivesCommandFor(me)) >> me

        def hasZeroLives = illidan().build()
        teamMemberCommandHandler.handle(aConsumeMyLifeCommand().with(hasZeroLives).build()) >> {
            throw new NotEnoughLivesException(hasZeroLives)
        }
    }

    static def restoreMyLivesCommandFor(TeamMember member) {
        def command = new RestoreMyLivesCommand()
        command.setOpenId(member.getOpenId())
        command
    }
}
