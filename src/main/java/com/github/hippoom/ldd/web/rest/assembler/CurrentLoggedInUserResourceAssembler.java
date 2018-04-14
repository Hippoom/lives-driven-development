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

    public Resource<User> toResource(Authentication authentication) {
        User me = new User();
        me.setDisplayName("Tyrande Whisperwind");
        me.setAvatar("https://vignette.wikia.nocookie.net/wowwiki/images/3/39/Tyrande-Whisperwind3.jpg/revision/latest?cb=20080901183433");
        Resource<User> resource = new Resource<>(me);
        resource.add(linkTo(methodOn(TeamMemberController.class).root()).withRel("teamMembers"));
        return resource;
    }
}
