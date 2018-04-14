package contracts.ldd.me

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'GET'
        url '/api/currentLoggedInUser'
        headers {
            contentType('application/json;charset=UTF-8')
        }
    }
    response {
        status 200
        body([
                displayName: $(producer("Tyrande Whisperwind")),
                avatar: $(producer("https://vignette.wikia.nocookie.net/wowwiki/images/3/39/Tyrande-Whisperwind3.jpg/revision/latest?cb=20080901183433")),
                _links     : [
                        teamMembers: [
                                href: $(producer("http://localhost/api/teamMembers"))
                        ]
                ]
        ])
    }
}
