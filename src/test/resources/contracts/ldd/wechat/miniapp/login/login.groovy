package contracts.ldd.wechat.miniapp.login

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url "/loginViaWeChatMiniApp?code=aWeChatMiniAppLoginCode"
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body(
                [
                        openId    : $(producer("Tyrande Whisperwind")),
                        sessionKey: $(producer("sessionKey")),
                        _links    : [
                                teamMembers: [
                                        href: $(producer("http://localhost/api/teamMembers"))
                                ]
                        ]
                ]
        )
    }
}
