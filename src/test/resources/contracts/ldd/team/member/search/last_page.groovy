package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/search?page=1&size=3"))
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
                        _embedded: [
                                [
                                        [
                                                displayName: "Jaina Proudmoore"
                                        ]
                                ]
                        ],
                        _links   : [
                                prev: [
                                        href: $(producer("http://localhost/api/teamMembers/search?page=0&size=3"))
                                ]
                        ]
                ]
        )
    }
}
