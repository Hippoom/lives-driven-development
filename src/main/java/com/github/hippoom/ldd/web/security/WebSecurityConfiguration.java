package com.github.hippoom.ldd.web.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hippoom.ldd.web.rest.assembler.CurrentLoggedInUserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("_halObjectMapper")
    private ObjectMapper halObjectMapper;

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private CurrentLoggedInUserResourceAssembler currentLoggedInUserResourceAssembler;

    @Autowired
    private JwtIssuer jwtIssuer;

    public static final String API_PATTERN = "/api/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.authorizeRequests()
                .antMatchers(API_PATTERN).authenticated()
                .anyRequest().permitAll()
        .and()
            .httpBasic()// for admin to access actuator
        .and()
            .logout()
            .logoutSuccessHandler(restAuthenticationEntryPoint())
        .and()
            .addFilterAfter(weChatMiniAppLoginFilter(), CsrfFilter.class)
            .addFilterAfter(weChatMpOAuth2AuthenticationProcessingFilter(), WeChatMiniAppLoginFilter.class)
            .csrf().disable() // add this back later depends on authentication by session or access token
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint());
            // @formatter:on
    }

    private Filter weChatMiniAppLoginFilter() {
        WeChatMiniAppLoginFilter filter = new WeChatMiniAppLoginFilter();
        filter.setWxMaService(wxMaService);
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler());
        return filter;
    }

    private Filter weChatMpOAuth2AuthenticationProcessingFilter() {

        JwtAuthenticationProcessingFilter filter =
                new JwtAuthenticationProcessingFilter(
                        new AntPathRequestMatcher(API_PATTERN)
                );
        filter.setJwtIssuer(jwtIssuer);
        filter.setAuthenticationSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
            // does nothing, just let the request pass
        });
        filter.setAuthenticationFailureHandler(restAuthenticationEntryPoint());
        return filter;
    }

    @Bean
    protected RestAuthenticationSuccessHandler restAuthenticationSuccessHandler() {
        return new RestAuthenticationSuccessHandler(halObjectMapper, jwtIssuer, currentLoggedInUserResourceAssembler);
    }

    @Bean
    protected RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint(halObjectMapper);
    }

}
