package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember

@WithTyrandeWhisperwind
abstract class MemberMeBase extends AbstractWebMvcTest {

    def setup() {
        def tyrande = aTeamMember()
                .withDisplayName("Tyrande Whisperwind")
                .withRemainingLives(3)
                .build()
        teamMemberRepository.mustFindBy("Tyrande Whisperwind") >> tyrande
    }
}
