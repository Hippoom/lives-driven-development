package com.github.hippoom.ldd.web

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@Import([
        MockMvcBuilderCustomizers,
        TestConfiguration
])
@WebMvcTest
abstract class AbstractWebMvcTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    def setup() {
        RestAssuredMockMvc.mockMvc(mockMvc)
    }

}
