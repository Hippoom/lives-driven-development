package com.github.hippoom.ldd.web.method.support;

import com.github.hippoom.ldd.web.security.support.CurrentLoggedInUserHandlerMethodArgumentResolver;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@EnableSpringDataWebSupport
@Configuration
@RequiredArgsConstructor
public class ArgumentResolversConfiguration extends WebMvcConfigurerAdapter {

    @NonNull
    private final CurrentLoggedInUserHandlerMethodArgumentResolver
        currentLoggedInUserHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentLoggedInUserHandlerMethodArgumentResolver);
    }
}
