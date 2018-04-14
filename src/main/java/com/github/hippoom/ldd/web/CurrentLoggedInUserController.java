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
        Resource<User> resource = new Resource<>(new User());
        resource.add(linkTo(methodOn(TeamMemberController.class).root()).withRel("teamMembers"));
        return resource;
    }

}
