package contracts.ldd.team.member.restore.lives

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/api/teamMembers/restoreMyLives"))
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
                        displayName   : "Illidan Stormrage",
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