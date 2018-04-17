package com.github.hippoom.ldd.jpa

import com.github.hippoom.ldd.eventhandling.EventPublisher
import com.github.hippoom.ldd.eventhandling.TeamMemberEvent
import com.github.hippoom.ldd.eventhandling.TeamMemberEventQuery
import com.github.hippoom.ldd.events.TeamMemberLifeConsumedEvent
import com.github.hippoom.ldd.events.TeamMemberLivesRestoredEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember

class JpaEventPublisherTest extends AbstractJpaTest {

    @Autowired
    private EventPublisher subject

    @Autowired
    private TeamMemberEventQuery teamMemberEventQuery

    def "it should save TeamMemberLifeConsumedEvent"() {
        given:
        def me = aTeamMember().build()
        def event = new TeamMemberLifeConsumedEvent(me.getId(), me.version, "why")

        and:
        def pageable = new PageRequest(0, 2)
        def before = teamMemberEventQuery.findBy(me.getId(), pageable)

        when:
        subject.publish(event)

        then:
        def after = teamMemberEventQuery.findBy(me.getId(), pageable)

        assert after.totalElements == before.totalElements + 1
        assertSorting(after, { TeamMemberEvent current, TeamMemberEvent next ->
            current.getSequence() >= next.getSequence()
        }, { Pageable nextPage ->
            subject.findBy(me.getId(), nextPage)
        })
    }

    def "it should save TeamMemberLivesRestoredEvent"() {
        given:
        def me = aTeamMember().build()
        def event = new TeamMemberLivesRestoredEvent(me.getId(), me.version, "how")

        and:
        def pageable = new PageRequest(0, 2)
        def before = teamMemberEventQuery.findBy(me.getId(), pageable)

        when:
        subject.publish(event)

        then:
        def after = teamMemberEventQuery.findBy(me.getId(), pageable)

        assert after.totalElements == before.totalElements + 1
        assertSorting(after, { TeamMemberEvent current, TeamMemberEvent next ->
            current.getSequence() >= next.getSequence()
        }, { Pageable nextPage ->
            subject.findBy(me.getId(), nextPage)
        })
    }
}
