package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/search?page=0&size=3"))
        headers {
            contentType('application/json;charset=UTF-8')
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
                                                })
                                                .number(2,
                                                {
                                                    it.displayName = "Malfurion Stormrage"
                                                    it.remainingLives = 2
                                                })
                                                .number(3,
                                                {
                                                    it.displayName = "Illidan Stormrage"
                                                    it.remainingLives = 0
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
