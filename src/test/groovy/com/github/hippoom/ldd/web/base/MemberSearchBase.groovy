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
        def firstPage = new PageRequest(0, 3)
        def firstPageElements = new PageImpl(
                listOfSize(3, { aTeamMember() })
                        .number(1, { it.withDisplayName("Tyrande Whisperwind") })
                        .number(2, { it.withDisplayName("Malfurion Stormrage") })
                        .number(3, { it.withDisplayName("Illidan Stormrage") })
                        .build(),
                firstPage,
                4
        )
        teamMemberRepository.findBy(firstPage) >> firstPageElements

        def lastPage = new PageRequest(1, 3)
        def lastPageElements = new PageImpl(
                listOfSize(1, { aTeamMember() })
                        .number(1, { it.withDisplayName("Jaina Proudmoore") })
                        .build(),
                lastPage,
                4
        )
        teamMemberRepository.findBy(lastPage) >> lastPageElements
    }
}
