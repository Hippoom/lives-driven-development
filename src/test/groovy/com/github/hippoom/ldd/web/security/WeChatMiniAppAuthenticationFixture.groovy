package com.github.hippoom.ldd.web.security

class WeChatMiniAppAuthenticationFixture {

    def static tyrandeWhisperwind() {
        def authentication = new WeChatMiniAppAuthentication()
        authentication.setOpenId("Tyrande Whisperwind")
        authentication.setSessionKey("sessionKey")
        authentication.setAuthenticated(true)
        authentication
    }
}
