package contracts.ldd.team.member.root

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/teamMembers'
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([
                _links: [
                        search: [
                                href: $(producer("http://localhost/api/teamMembers/search"))
                        ]
                ]
        ])
    }
}
