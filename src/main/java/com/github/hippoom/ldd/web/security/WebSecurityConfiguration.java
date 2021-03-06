package com.github.hippoom.ldd.web.security;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hippoom.ldd.web.rest.assembler.CurrentLoggedInUserResourceAssembler;
import lombok.Setter;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.Filter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@ConfigurationProperties(prefix = "security")
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

    @Setter
    private String adminPassword;
    @Setter
    private String hashidsSalt;

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
            .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
        .and()
            .sessionManagement().sessionCreationPolicy(STATELESS);
            // @formatter:on
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(adminPassword)
                .roles("ACTUATOR");
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

    @Bean
    protected Hashids hashids() {
        return new Hashids(hashidsSalt);
    }
}
