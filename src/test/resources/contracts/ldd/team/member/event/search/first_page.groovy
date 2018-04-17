package ldd.team.member.event.search

import org.springframework.cloud.contract.spec.Contract

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/3/events?page=0&size=3"))
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
                                events: $(producer(
                                        listOfSize(3, { [:] })
                                                .number(1,
                                                {
                                                    it.when = 1522573200
                                                    it.payload = [
                                                            why: "I missed the stand up meeting"
                                                    ]
                                                })
                                                .number(2,
                                                {
                                                    it.when = 1522569600
                                                    it.payload = [
                                                            why: "I forgot moving the story card"
                                                    ]
                                                })
                                                .number(3,
                                                {
                                                    it.when = 1522566000
                                                    it.payload = [
                                                            why: "I missed the show case"
                                                    ]
                                                })
                                                .build { it }
                                ))
                        ],
                        _links   : [
                                next: [
                                        href: $(producer("http://localhost/api/teamMembers/3/events?page=1&size=3"))
                                ]
                        ]
                ]
        )
    }
}
