package com.github.hippoom.ldd.web.rest;

import com.github.hippoom.ldd.model.User;
import com.github.hippoom.ldd.web.rest.assembler.CurrentLoggedInUserResourceAssembler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(produces = APPLICATION_JSON_UTF8_VALUE)
public class CurrentLoggedInUserController {

    @NonNull
    private final CurrentLoggedInUserResourceAssembler currentLoggedInUserResourceAssembler;

    @GetMapping(path = "/api/currentLoggedInUser")
    public Resource<User> me(Authentication authentication) {
        return currentLoggedInUserResourceAssembler.toResource(authentication);
    }

}
