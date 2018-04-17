package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/search?page=0&size=3"))
        headers {
            contentType('application/json;charset=UTF-8')
            header "Authorization": ($(producer("Bearer Tyrande")))
        }
    }
    response {
        status 200
        body(
                [
                        _embedded: [
                                teamMembers: $(producer(
                                        listOfSize(3, { [:] })
                                                .number(1,
                                                {
                                                    it.displayName = "Tyrande Whisperwind"
                                                    it.remainingLives = 3
                                                    it._links = [
                                                            self: [
                                                                  href: "http://localhost/api/teamMembers/1"
                                                            ]
                                                    ]
                                                })
                                                .number(2,
                                                {
                                                    it.displayName = "Malfurion Stormrage"
                                                    it.remainingLives = 2
                                                    it._links = [
                                                            self: [
                                                                    href: "http://localhost/api/teamMembers/2"
                                                            ]
                                                    ]
                                                })
                                                .number(3,
                                                {
                                                    it.displayName = "Illidan Stormrage"
                                                    it.remainingLives = 0
                                                    it._links = [
                                                            self: [
                                                                    href: "http://localhost/api/teamMembers/3"
                                                            ]
                                                    ]
                                                })
                                                .build { it }
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
