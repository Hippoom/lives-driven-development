package contracts.ldd.team.member.me.illidan

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/me"))
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body(
                [
                        displayName   : "Illidan Stormrage",
                        remainingLives: 0,
                        _links        : [
                                restoreMyLives: [
                                        href: $(producer("http://localhost/api/teamMembers/restoreMyLives"))
                                ]
                        ]
                ]
        )
    }
}
