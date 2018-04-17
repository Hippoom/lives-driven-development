package com.github.hippoom.ldd.web.rest;

import com.github.hippoom.ldd.commandhandling.TeamMemberCommandHandler;
import com.github.hippoom.ldd.commands.ConsumeMyLifeCommand;
import com.github.hippoom.ldd.commands.RestoreMyLivesCommand;
import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import com.github.hippoom.ldd.web.security.annotation.CurrentLoggedInUser;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hashids.Hashids;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
    private final TeamMemberCommandHandler teamMemberCommandHandler;

    @NonNull
    private final PagedResourcesAssembler<TeamMember> pagedResourcesAssembler;

    @NonNull
    private final Hashids hashids;

    @SuppressFBWarnings(value = "NP", justification = "it is intended for assembling template link")
    @GetMapping
    public Resource<String> root() {
        Resource<String> resource = new Resource<>("");
        resource.add(templateLink("search",
                linkTo(methodOn(TeamMemberController.class).search(null)),
                pagedTemplateVariables()));
        resource.add(linkTo(methodOn(TeamMemberController.class).me(null)).withRel("me"));
        return resource;
    }

    @GetMapping(path = "/search")
    public PagedResources<Resource<TeamMember>> search(@PageableDefault(size = 15) Pageable pageable) {
        Page<TeamMember> paged = teamMemberRepository.findBy(pageable);
        PagedResources<Resource<TeamMember>> resources = pagedResourcesAssembler.toResource(paged);
        resources.forEach(r -> r.add(linkTo(methodOn(TeamMemberController.class).findBy(hashids.encode(r.getContent().getId()))).withSelfRel()));
        return resources;
    }

    @SuppressWarnings("ConstantConditions")
    @GetMapping(path = "/me")
    public Resource<TeamMember> me(@CurrentLoggedInUser TeamMember me) {
        return toResource(me);
    }

    @SuppressWarnings("ConstantConditions")
    @GetMapping(path = "/{id}")
    public Resource<TeamMember> findBy(@PathVariable String id) {
        return toResource(teamMemberRepository.mustFindBy(hashids.decode(id)[0]));
    }

    private Resource<TeamMember> toResource(@CurrentLoggedInUser TeamMember me) {
        Resource<TeamMember> resource = new Resource<>(me);
        resource.add(me.hasRemainingLives() ? linkToConsumeMyLife() : linkToRestoreMyLives());
        resource.add(linkTo(methodOn(TeamMemberEventController.class).search(hashids.encode(me.getId()), null))
                .withRel("events"));
        return resource;
    }

    private Link linkToConsumeMyLife() {
        return linkTo(methodOn(TeamMemberController.class).handle(new ConsumeMyLifeCommand()))
                .withRel("consumeMyLife");
    }

    private Link linkToRestoreMyLives() {
        return linkTo(methodOn(TeamMemberController.class).handle(new RestoreMyLivesCommand()))
                .withRel("restoreMyLives");
    }

    @PostMapping(path = "/consumeMyLife")
    public Resource<TeamMember> handle(@Valid @CurrentLoggedInUser @RequestBody ConsumeMyLifeCommand command) {
        return toResource(teamMemberCommandHandler.handle(command));
    }

    @PostMapping(path = "/restoreMyLives")
    public Resource<TeamMember> handle(@Valid @CurrentLoggedInUser @RequestBody RestoreMyLivesCommand command) {
        return toResource(teamMemberCommandHandler.handle(command));
    }
}
