package com.github.hippoom.ldd.web.security

import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.illidanStormrage

class IllidanStormrage extends AbstractWithSecurityContextFactory<WithIllidanStormrage> {
    @Override
    WeChatMiniAppAuthentication currentLoggedInUser() {
        illidanStormrage()
    }
}
