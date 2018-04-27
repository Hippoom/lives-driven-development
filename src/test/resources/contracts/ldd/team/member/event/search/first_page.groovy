package contracts.ldd.team.member.event.search

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/Vp/events?page=0&size=3"))
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
                                events: $(producer(
                                        [
                                                [
                                                        when   : 1522573200,
                                                        type   : "LivesRestored",
                                                        payload: [
                                                                how: "I bought milk tea for the team on April 1st"
                                                        ]
                                                ],
                                                [
                                                        when   : 1522569600,
                                                        type   : "LifeConsumed",
                                                        payload: [
                                                                why: "I forgot moving the story card"
                                                        ]
                                                ],
                                                [
                                                        when   : 1522566000,
                                                        type   : "LifeConsumed",
                                                        payload: [
                                                                why: "I missed the show case"
                                                        ]
                                                ]
                                        ]
                                ))
                        ],
                        _links   : [
                                next: [
                                        href: $(producer("http://localhost/api/teamMembers/Vp/events?page=1&size=3"))
                                ]
                        ]
                ]
        )
    }
}
