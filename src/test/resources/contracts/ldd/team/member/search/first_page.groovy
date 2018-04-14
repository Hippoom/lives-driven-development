package contracts.ldd.team.member.search

import org.springframework.cloud.contract.spec.Contract

import static com.github.hippoom.tdb.GenericTestDataListBuilder.listOfSize

Contract.make {
    request {
        method 'GET'
        url '/api/teamMembers/search'
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
                                                .number(1, { it.displayName = "Tyrande Whisperwind" })
                                                .number(2, { it.displayName = "Malfurion Stormrage" })
                                                .number(3, { it.displayName = "Illidan Stormrage" })
                                                .build { it }
                                ))
                        ]
                ]
        )
    }
}
