package contracts.ldd.team.member.root

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/teamMembers'
        headers {
            header "Authorization": ($(producer("Bearer Tyrande")))
        }
    }
    response {
        status 200
        headers {
            contentType('application/json;charset=UTF-8')
        }
        body([
                _links: [
                        search: [
                                href     : $(producer("http://localhost/api/teamMembers/search{?page,size}")),
                                templated: true
                        ],
                        me    : [
                                href: $(producer("http://localhost/api/teamMembers/me"))
                        ]
                ]
        ])
    }
}
