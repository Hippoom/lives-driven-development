package contracts.ldd.team.member.consume.life

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/api/teamMembers/consumeMyLife"))
        headers {
            contentType('application/json;charset=UTF-8')
            header "Authorization": ($(producer("Bearer Tyrande")))
        }
        body(
                [
                        why: $(producer("I missed the stand up meeting"))
                ]
        )
    }
    response {
        status 200
        headers {
            contentType('application/json;charset=UTF-8')
        }
        body(
                [
                        displayName   : "Tyrande Whisperwind",
                        remainingLives: 2,
                        _links        : [
                                consumeMyLife: [
                                        href: $(producer("http://localhost/api/teamMembers/consumeMyLife"))
                                ]
                        ]
                ]
        )
    }
}
