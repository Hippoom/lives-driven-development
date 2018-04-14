package com.github.hippoom.ldd.web

import cn.binarywang.wx.miniapp.api.WxMaService
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
    public WxMaService wxMaService() {
        return factory.Mock(WxMaService)
    }

}
