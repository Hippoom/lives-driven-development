package com.github.hippoom.ldd.web.security

import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.tyrandeWhisperwind

class TyrandeWhisperwind extends AbstractWithSecurityContextFactory<WithTyrandeWhisperwind> {
    @Override
    WeChatMiniAppAuthentication currentLoggedInUser() {
        tyrandeWhisperwind()
    }
}
