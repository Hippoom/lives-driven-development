package com.github.hippoom.ldd.jpa

import com.github.hippoom.ldd.model.TeamMember
import com.github.hippoom.ldd.model.TeamMemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

import static com.github.hippoom.ldd.model.TeamMemberFixture.aTeamMember

class JpaTeamMemberRepositoryTest extends AbstractJpaTest {

    @Autowired
    private TeamMemberRepository subject

    def "it should return paged elements"() {
        given:
        def pageable = new PageRequest(0, 2)
        def before = subject.findBy(pageable)

        and:
        subject.save(
                aTeamMember().build(),
                aTeamMember().build(),
                aTeamMember().build()
        )

        when:
        def after = subject.findBy(pageable)

        then:
        assert after.totalElements == before.totalElements + 3
        assertSorting(after, { TeamMember current, TeamMember next ->
            current.getDisplayName() <= next.getDisplayName()
        }, { Pageable nextPage ->
            subject.findBy(nextPage)
        })
    }

    def "it should find by openId"() {
        given:
        def expect = aTeamMember().build()
        subject.save(expect)

        when:
        def actual = subject.mustFindBy(expect.getOpenId())

        then:
        assert actual != null
    }
}
