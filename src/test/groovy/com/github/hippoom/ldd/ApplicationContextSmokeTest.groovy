package com.github.hippoom.ldd

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class ApplicationContextSmokeTest extends Specification {

    def "it should load the context"() {

        when: "the application context starts"
        // should fail if wiring fails

        then: "no wiring exceptions are thrown"

    }
}
