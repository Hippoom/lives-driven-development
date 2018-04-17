package com.github.hippoom.ldd.web

import cn.binarywang.wx.miniapp.api.WxMaService
import cn.binarywang.wx.miniapp.api.WxMaUserService
import com.github.hippoom.ldd.commandhandling.TeamMemberCommandHandler
import com.github.hippoom.ldd.eventhandling.TeamMemberEventQuery
import com.github.hippoom.ldd.model.TeamMemberRepository
import com.github.hippoom.ldd.web.security.JwtIssuer
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.illidanStormrage
import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.tyrandeWhisperwind

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

    @Autowired
    protected TeamMemberRepository teamMemberRepository

    @Autowired
    protected TeamMemberCommandHandler teamMemberCommandHandler

    @Autowired
    protected TeamMemberEventQuery teamMemberEventQuery

    @Autowired
    protected JwtIssuer jwtIssuer

    def setup() {
        RestAssuredMockMvc.mockMvc(mockMvc)
        wxMaService.getUserService() >> wxMaUserService
        def tyrande = tyrandeWhisperwind()
        jwtIssuer.generateWith(tyrande) >> "Tyrande"
        jwtIssuer.verify("Tyrande") >> tyrande
        def illidan = illidanStormrage()
        jwtIssuer.generateWith(illidan) >> "Illidan"
        jwtIssuer.verify("Illidan") >> illidan
    }

}
