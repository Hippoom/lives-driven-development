package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind

import static com.github.hippoom.ldd.commands.ConsumeMyLifeCommandFixture.aConsumeMyLifeCommand
import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

@WithTyrandeWhisperwind
abstract class ConsumeLifeBase extends AbstractWebMvcTest {

    def setup() {
        def tyrande = tyrande().but()
                .withRemainingLives(2)
                .build()
        teamMemberCommandHandler.handle(aConsumeMyLifeCommand().with(tyrande).build()) >> tyrande
    }
}
