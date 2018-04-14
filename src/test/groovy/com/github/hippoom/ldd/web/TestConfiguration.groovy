package com.github.hippoom.ldd.web

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import spock.mock.DetachedMockFactory

@ComponentScan([
        "com.github.hippoom.ldd.web"
])
@Configuration
class TestConfiguration {

    private DetachedMockFactory factory = new DetachedMockFactory()

}
