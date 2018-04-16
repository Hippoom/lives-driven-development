package contracts.ldd.team.member.restore.lives

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url $(producer("/api/teamMembers/consumeMyLife"))
        headers {
            contentType('application/json;charset=UTF-8')
        }
        body(
                [
                        reason: $(producer("I missed the stand up meeting"))
                ]
        )
    }
    response {
        status 412
    }
}
