package com.github.hippoom.ldd.web.rest;

import com.github.hippoom.ldd.eventhandling.TeamMemberEvent;
import com.github.hippoom.ldd.eventhandling.TeamMemberEventQuery;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/teamMembers", produces = APPLICATION_JSON_UTF8_VALUE)
public class TeamMemberEventController {

    @NonNull
    private final TeamMemberEventQuery teamMemberEventQuery;

    @NonNull
    private final PagedResourcesAssembler<TeamMemberEvent> pagedResourcesAssembler;

    @NonNull
    private final Hashids hashids;

    @GetMapping(path = "/{id}/events")
    public PagedResources<Resource<TeamMemberEvent>> search(
            @PathVariable String id,
            @PageableDefault(size = 15) Pageable pageable) {
        return pagedResourcesAssembler.toResource(teamMemberEventQuery.findBy(hashids.decode(id)[0], pageable));
    }
}
