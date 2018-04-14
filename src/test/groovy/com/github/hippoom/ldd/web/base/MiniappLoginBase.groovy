package com.github.hippoom.ldd.web.base

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult
import com.github.hippoom.ldd.web.AbstractWebMvcTest

class MiniappLoginBase extends AbstractWebMvcTest {

    def setup() {
        WxMaJscode2SessionResult sessionResult = defaultWxMaJscode2SessionResult()
        super.wxMaUserService.getSessionInfo("aWeChatMiniAppLoginCode") >> sessionResult
    }

    def defaultWxMaJscode2SessionResult() {
        def sessionResult = new WxMaJscode2SessionResult()
        sessionResult.setOpenid("Tyrande Whisperwind")
        sessionResult.setSessionKey("sessionKey")
        sessionResult
    }
}
