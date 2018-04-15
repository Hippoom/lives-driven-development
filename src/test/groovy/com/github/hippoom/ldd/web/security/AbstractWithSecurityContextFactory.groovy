package com.github.hippoom.ldd.web.security

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

import java.lang.annotation.Annotation

abstract class AbstractWithSecurityContextFactory<T extends Annotation> implements WithSecurityContextFactory<T> {
    @Override
    SecurityContext createSecurityContext(T annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext()
        context.setAuthentication(currentLoggedInUser())
        context
    }

    abstract WeChatMiniAppAuthentication currentLoggedInUser()
}
