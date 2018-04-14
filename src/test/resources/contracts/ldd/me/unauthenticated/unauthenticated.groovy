package contracts.ldd.me.unauthenticated

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/currentLoggedInUser'
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 401
        body([
                _links: [
                        loginViaWeChatMiniApp: [
                                href: $(producer("http://localhost/loginViaWeChatMiniApp"))
                        ]
                ]
        ])
    }
}
