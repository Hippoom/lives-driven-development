package contracts.ldd.wechat.miniapp.login

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/loginViaWeChatMiniApp?code=Tyrande"))
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        headers {
            header 'Authorization': "Bearer Tyrande"
        }
        body(
                [
                        _links: [
                                teamMembers: [
                                        href: $(producer("http://localhost/api/teamMembers"))
                                ]
                        ]
                ]
        )
    }
}
