package com.github.hippoom.ldd.web;

import com.github.hippoom.ldd.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class CurrentLoggedInUserController {

    @GetMapping(path = "/api/currentLoggedInUser")
    public Resource<User> me() {
        User me = new User();
        me.setDisplayName("Tyrande Whisperwind");
        me.setAvatar("https://vignette.wikia.nocookie.net/wowwiki/images/3/39/Tyrande-Whisperwind3.jpg/revision/latest?cb=20080901183433");
        Resource<User> resource = new Resource<>(me);
        resource.add(linkTo(methodOn(TeamMemberController.class).root()).withRel("teamMembers"));
        return resource;
    }

}
