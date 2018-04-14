package contracts.ldd.me.authenticated

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
        status 200
        body([
                openId    : $(producer("Tyrande Whisperwind")),
                sessionKey: $(producer("sessionKey")),
                _links    : [
                        teamMembers: [
                                href: $(producer("http://localhost/api/teamMembers"))
                        ]
                ]
        ])
    }
}
