package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

import java.time.LocalDateTime

import static com.github.hippoom.ldd.events.TeamMemberLifeConsumedEventFixture.aLifeConsumedEvent
import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan
import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

@WithTyrandeWhisperwind
abstract class EventSearchBase extends AbstractWebMvcTest {

    def setup() {
        def illidan = illidan().build()
        def firstPage = new PageRequest(0, 3)
        def firstPageElements = new PageImpl(
                listOfSize(3, { aLifeConsumedEvent() })
                        .number(1,
                        {
                            it.withSequence(3)
                            it.with(illidan)
                            it.withWhy("I missed the stand up meeting")
                            it.when(LocalDateTime.of(2018, 4, 1, 17, 0, 0))
                        })
                        .number(2,
                        {
                            it.withSequence(2)
                            it.with(illidan)
                            it.withWhy("I forgot moving the story card")
                            it.when(LocalDateTime.of(2018, 4, 1, 16, 0, 0))
                        })
                        .number(3,
                        {
                            it.withSequence(1)
                            it.with(illidan)
                            it.withWhy("I missed the show case")
                            it.when(LocalDateTime.of(2018, 4, 1, 15, 0, 0))
                        })
                        .build { it.buildEvent() },
                firstPage,
                4
        )
        teamMemberEventQuery.findBy(illidan.getId(), firstPage) >> firstPageElements
    }
}
