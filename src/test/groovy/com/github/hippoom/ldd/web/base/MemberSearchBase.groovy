package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember
import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

@WithTyrandeWhisperwind
abstract class MemberSearchBase extends AbstractWebMvcTest {

    def setup() {
        def pageable = new PageRequest(0, 3)
        def firstPage = new PageImpl(
                listOfSize(3, { aTeamMember() })
                        .number(1, { it.withDisplayName("Tyrande Whisperwind") })
                        .number(2, { it.withDisplayName("Malfurion Stormrage") })
                        .number(3, { it.withDisplayName("Illidan Stormrage") })
                        .build(),
                pageable,
                4
        )
        teamMemberRepository.findBy(pageable) >> firstPage
    }
}
