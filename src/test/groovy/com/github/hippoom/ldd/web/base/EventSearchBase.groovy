package com.github.hippoom.ldd.web.base

import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WithTyrandeWhisperwind
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest

import java.time.LocalDateTime

import static com.github.hippoom.ldd.events.TeamMemberLifeConsumedEventFixture.aLifeConsumedEvent
import static com.github.hippoom.ldd.events.TeamMemberLivesRestoredEventFixture.aLivesRestoredEvent
import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan

@WithTyrandeWhisperwind
abstract class EventSearchBase extends AbstractWebMvcTest {

    def setup() {
        def illidan = illidan().build()
        def firstPage = new PageRequest(0, 3)
        def firstPageElements = new PageImpl(
                [
                        aLivesRestoredEvent()
                                .withSequence(3)
                                .with(illidan)
                                .withHow("I bought milk tea for the team on April 1st")
                                .when(LocalDateTime.of(2018, 4, 1, 17, 0, 0))
                                .buildEvent(),
                        aLifeConsumedEvent()
                                .withSequence(2)
                                .with(illidan)
                                .withWhy("I forgot moving the story card")
                                .when(LocalDateTime.of(2018, 4, 1, 16, 0, 0))
                                .buildEvent(),
                        aLifeConsumedEvent()
                                .withSequence(1)
                                .with(illidan)
                                .withWhy("I missed the show case")
                                .when(LocalDateTime.of(2018, 4, 1, 15, 0, 0))
                                .buildEvent()

                ],
                firstPage,
                4
        )
        teamMemberEventQuery.findBy(illidan.getId(), firstPage) >> firstPageElements
    }

}
