package com.github.hippoom.ldd.jpa

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Page
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Import([
        JpaConfiguration
])
@Transactional(propagation = NOT_SUPPORTED)
@ActiveProfiles("test")
abstract class AbstractJpaTest extends Specification {

    @PersistenceContext
    protected EntityManager entityManager

    @Autowired
    protected PlatformTransactionManager transactionManager

    protected void assertSorting(Page<?> page,
                                 Closure sortingAssertion,
                                 Closure nextPageRetriever) {
        def content = page.getContent()
        assert content.size() <= page.getSize()
        0.upto(content.size() - 1, { index ->
            def current = content.get(index)
            if (content.size() > index + 1) {
                def next = content.get(index + 1)
                sortingAssertion(current, next)
            }
        })
        if (page.hasNext()) {
            Page<?> next = nextPageRetriever(page.nextPageable())
            assertSorting(next, sortingAssertion, nextPageRetriever)
        }
    }
}
