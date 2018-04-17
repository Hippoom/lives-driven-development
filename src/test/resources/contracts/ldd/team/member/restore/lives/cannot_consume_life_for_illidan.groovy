package contracts.ldd.team.member.restore.lives

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/api/teamMembers/consumeMyLife"))
        headers {
            contentType('application/json;charset=UTF-8')
            header "Authorization": ($(producer("Bearer Illidan")))
        }
        body(
                [
                        why: $(producer("I missed the stand up meeting"))
                ]
        )
    }
    response {
        status 412
    }
}
