package contracts.ldd.team.member.me.illidan

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/me"))
        headers {
            header "Authorization": ($(producer("Bearer Illidan")))
        }
    }
    response {
        status 200
        headers {
            contentType('application/json;charset=UTF-8')
        }
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
