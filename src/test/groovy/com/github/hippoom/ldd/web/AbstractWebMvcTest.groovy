package com.github.hippoom.ldd.web

import cn.binarywang.wx.miniapp.api.WxMaService
import cn.binarywang.wx.miniapp.api.WxMaUserService
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

@Import([
        MockMvcBuilderCustomizers,
        WebMvcTestConfiguration
])
@WebMvcTest
abstract class AbstractWebMvcTest extends Specification {

    @Autowired
    protected MockMvc mockMvc

    @Autowired
    protected WxMaService wxMaService

    protected WxMaUserService wxMaUserService = Mock()

    def setup() {
        RestAssuredMockMvc.mockMvc(mockMvc)
        wxMaService.getUserService() >> wxMaUserService
    }

}
