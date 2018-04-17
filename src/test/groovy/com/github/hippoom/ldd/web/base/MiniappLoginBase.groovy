package com.github.hippoom.ldd.web.base

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult
import com.github.hippoom.ldd.web.AbstractWebMvcTest
import com.github.hippoom.ldd.web.security.WeChatMiniAppAuthentication

import static com.github.hippoom.ldd.web.security.WeChatMiniAppAuthenticationFixture.tyrandeWhisperwind

class MiniappLoginBase extends AbstractWebMvcTest {

    def setup() {
        WxMaJscode2SessionResult sessionResult = defaultWxMaJscode2SessionResult(tyrandeWhisperwind())
        super.wxMaUserService.getSessionInfo("Tyrande") >> sessionResult
    }

    def defaultWxMaJscode2SessionResult(WeChatMiniAppAuthentication authentication) {
        def sessionResult = new WxMaJscode2SessionResult()
        sessionResult.setOpenid(authentication.getOpenId())
        sessionResult.setSessionKey(authentication.getSessionKey())
        sessionResult.setUnionid(authentication.getUnionId())
        sessionResult
    }
}
