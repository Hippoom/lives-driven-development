package com.github.hippoom.ldd.web.rest;

import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.github.hippoom.ldd.web.rest.assembler.Links.pagedTemplateVariables;
import static com.github.hippoom.ldd.web.rest.assembler.Links.templateLink;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/teamMembers", produces = APPLICATION_JSON_UTF8_VALUE)
public class TeamMemberController {

    @NonNull
    private final TeamMemberRepository teamMemberRepository;

    @NonNull
    private final PagedResourcesAssembler<TeamMember> pagedResourcesAssembler;

    @GetMapping
    public Resource<String> root() {
        Resource<String> resource = new Resource<>("");
        resource.add(templateLink("search",
                linkTo(methodOn(TeamMemberController.class).search(null)),
                pagedTemplateVariables()));
        return resource;
    }

    @GetMapping(path = "/search")
    public PagedResources<Resource<TeamMember>> search(@PageableDefault(size = 15) Pageable pageable) {
        Page<TeamMember> paged = teamMemberRepository.findBy(pageable);
        return pagedResourcesAssembler.toResource(paged);
    }
}
