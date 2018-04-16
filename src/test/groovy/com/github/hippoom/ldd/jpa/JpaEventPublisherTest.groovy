package com.github.hippoom.ldd.jpa

import com.github.hippoom.ldd.eventhandling.EventPublisher
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent
import com.github.hippoom.ldd.eventhandling.TeamMemberEventQuery
import com.github.hippoom.ldd.events.TeamMemberLifeConsumedEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember

class JpaEventPublisherTest extends AbstractJpaTest {

    @Autowired
    private EventPublisher subject

    @Autowired
    private TeamMemberEventQuery teamMemberEventQuery

    def "it should save event"() {
        given:
        def me = aTeamMember().build()
        def event = new TeamMemberLifeConsumedEvent(me.getOpenId(), me.version, "reason")

        and:
        def pageable = new PageRequest(0, 2)
        def before = teamMemberEventQuery.findBy(me.getOpenId(), pageable)

        when:
        subject.publish(event)

        then:
        def after = teamMemberEventQuery.findBy(me.getOpenId(), pageable)

        assert after.totalElements == before.totalElements + 1
        assertSorting(after, { TeamMemberEvent current, TeamMemberEvent next ->
            current.getSequence() >= next.getSequence()
        }, { Pageable nextPage ->
            subject.findBy(me.getOpenId(), nextPage)
        })
    }
}
