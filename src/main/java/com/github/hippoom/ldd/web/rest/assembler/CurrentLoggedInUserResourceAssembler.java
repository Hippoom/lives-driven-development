package com.github.hippoom.ldd.web.rest.assembler;

import com.github.hippoom.ldd.model.User;
import com.github.hippoom.ldd.web.rest.TeamMemberController;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CurrentLoggedInUserResourceAssembler {

    public Resource<Authentication> toResource(Authentication authentication) {
        Resource<Authentication> resource = new Resource<>(authentication);
        resource.add(linkTo(methodOn(TeamMemberController.class).root()).withRel("teamMembers"));
        return resource;
    }
}
