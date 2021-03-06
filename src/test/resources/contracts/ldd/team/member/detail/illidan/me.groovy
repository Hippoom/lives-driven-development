package contracts.ldd.team.member.detail.illidan

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/Vp"))
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
                        displayName   : "Illidan Stormrage",
                        remainingLives: 0,
                        _links        : [
                                restoreMyLives: [
                                        href: $(producer("http://localhost/api/teamMembers/restoreMyLives"))
                                ],
                                events        : [
                                        href: $(producer("http://localhost/api/teamMembers/Vp/events"))
                                ]
                        ]
                ]
        )
    }
}
