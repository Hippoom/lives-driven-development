package contracts.ldd.wechat.miniapp.login

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/loginViaWeChatMiniApp?code=Tyrande"))
    }
    response {
        status 200
        headers {
            header 'Authorization': "Bearer Tyrande"
            contentType('application/json')
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
