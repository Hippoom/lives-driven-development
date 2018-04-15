package contracts.ldd.team.member.consume.life

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/api/teamMembers/consumeMyLife"))
        headers {
            contentType('application/json;charset=UTF-8')
        }
        body(
                "{}" // empty json
        )
    }
    response {
        status 200
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
