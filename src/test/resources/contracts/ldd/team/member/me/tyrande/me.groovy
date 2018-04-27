package contracts.ldd.team.member.me.tyrande

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/me"))
        headers {
            header "Authorization": ($(producer("Bearer Tyrande")))
        }
    }
    response {
        status 200
        headers {
            contentType('application/json;charset=UTF-8')
        }
        body(
                [
                        displayName   : "Tyrande Whisperwind",
                        remainingLives: 3,
                        _links        : [
                                consumeMyLife: [
                                        href: $(producer("http://localhost/api/teamMembers/consumeMyLife"))
                                ]
                        ]
                ]
        )
    }
}
