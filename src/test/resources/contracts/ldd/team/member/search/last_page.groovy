package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

Contract.make {
    request {
        method 'GET'
        url $(producer("/api/teamMembers/search?page=1&size=3"))
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
                                        listOfSize(1, { [:] })
                                                .number(1, { it.displayName = "Jaina Proudmoore" })
                                                .build { it }
                                ))
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
