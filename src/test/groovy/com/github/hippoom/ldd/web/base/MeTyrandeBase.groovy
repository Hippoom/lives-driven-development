package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind

import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

@WithTyrandeWhisperwind
abstract class MeTyrandeBase extends AbstractWebMvcTest {

    def setup() {
        def tyrande = tyrande().build()
        teamMemberRepository.mustFindByOpenId(tyrande.getOpenId()) >> tyrande
    }
}
