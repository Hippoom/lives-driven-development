package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/search?page=0&size=3"))
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
                                teamMembers: $(producer(
                                        [
                                                [
                                                        displayName   : "Tyrande Whisperwind",
                                                        remainingLives: 3,
                                                        _links        : [
                                                                self: [
                                                                        href: "http://localhost/api/teamMembers/oW"
                                                                ]
                                                        ]
                                                ],
                                                [
                                                        displayName   : "Malfurion Stormrage",
                                                        remainingLives: 2,
                                                        _links        : [
                                                                self: [
                                                                        href: "http://localhost/api/teamMembers/nm"
                                                                ]
                                                        ]
                                                ],
                                                [
                                                        displayName   : "Illidan Stormrage",
                                                        remainingLives: 0,
                                                        _links        : [
                                                                self: [
                                                                        href: "http://localhost/api/teamMembers/Vp"
                                                                ]
                                                        ]
                                                ]
                                        ]
                                ))
                        ],
                        _links   : [
                                next: [
                                        href: $(producer("http://localhost/api/teamMembers/search?page=1&size=3"))
                                ]
                        ]
                ]
        )
    }
}
