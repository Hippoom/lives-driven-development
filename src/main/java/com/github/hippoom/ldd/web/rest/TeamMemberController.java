package com.github.hippoom.ldd.web.rest;

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
@RequestMapping(path = "/api/teamMembers", produces = APPLICATION_JSON_UTF8_VALUE)
public class TeamMemberController {

    @GetMapping
    public Resource<String> root() {
        Resource<String> resource = new Resource<>("");
        resource.add(linkTo(methodOn(TeamMemberController.class).search()).withRel("search"));
        return resource;
    }

    @GetMapping(path = "/search")
    public Resource search() {
        return null;
    }
}
