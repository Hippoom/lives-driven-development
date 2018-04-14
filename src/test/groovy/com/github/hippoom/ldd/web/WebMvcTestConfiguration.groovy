package com.github.hippoom.ldd.web

import cn.binarywang.wx.miniapp.api.WxMaService
import com.github.hippoom.ldd.model.TeamMemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import spock.mock.DetachedMockFactory

@ComponentScan([
        "com.github.hippoom.ldd.web"
])
@Configuration
class WebMvcTestConfiguration {

    private DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    WxMaService wxMaService() {
        factory.Mock(WxMaService)
    }

    @Bean
    TeamMemberRepository teamMemberRepository() {
        factory.Mock(TeamMemberRepository)
    }

}
