package contracts.ldd.wechat.miniapp.login

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    ignored()
    request {
        method 'POST'
        url $(producer("/loginViaWeChatMiniApp?code=notmember"))
    }
    response {
        status 200
        headers {
            header 'Authorization': "Bearer notmember"
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
