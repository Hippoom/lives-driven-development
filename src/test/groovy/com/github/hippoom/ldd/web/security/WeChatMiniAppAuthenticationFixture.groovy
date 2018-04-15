package com.github.hippoom.ldd.web.security

import com.github.hippoom.ldd.model.TeamMember

import static com.github.hippoom.ldd.model.TeamMemberFixture.illidan
import static com.github.hippoom.ldd.model.TeamMemberFixture.tyrande

class WeChatMiniAppAuthenticationFixture {

    def static tyrandeWhisperwind() {
        defaultAuthenticationBut(tyrande().build())
    }

    static def defaultAuthenticationBut(TeamMember tyrande) {
        def authentication = new WeChatMiniAppAuthentication()
        authentication.setOpenId(tyrande.getOpenId())
        authentication.setSessionKey("sessionKey")
        authentication.setAuthenticated(true)
        authentication
    }

    def static illidanStormrage() {
        defaultAuthenticationBut(illidan().build())
    }
}
