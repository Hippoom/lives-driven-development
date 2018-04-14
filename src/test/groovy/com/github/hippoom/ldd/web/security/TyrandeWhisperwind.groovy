package com.github.hippoom.ldd.web.security

import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.test.context.support.WithSecurityContextFactory

import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.tyrandeWhisperwind

class TyrandeWhisperwind implements WithSecurityContextFactory<WithTyrandeWhisperwind> {
    @Override
    SecurityContext createSecurityContext(WithTyrandeWhisperwind annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext()
        context.setAuthentication(tyrandeWhisperwind())
        context
    }
}
