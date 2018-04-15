package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithIllidanStormrage

import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan

@WithIllidanStormrage
abstract class MeIllidanBase extends AbstractWebMvcTest {

    def setup() {
        def illidan = illidan().build()
        teamMemberRepository.mustFindBy(illidan.getOpenId()) >> illidan
    }
}
