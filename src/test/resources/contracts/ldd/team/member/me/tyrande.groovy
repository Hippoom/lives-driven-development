package contracts.ldd.team.member.me

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
                        displayName   : "Tyrande Whisperwind",
                        remainingLives: 3
                ]
        )
    }
}
