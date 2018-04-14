package com.github.hippoom.ldd.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hippoom.ldd.web.rest.assembler.CurrentLoggedInUserResourceAssembler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.hippoom.ldd.web.security.HttpResponseRenderer.reply;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @NonNull
    private final ObjectMapper objectMapper;
    @NonNull
    private final CurrentLoggedInUserResourceAssembler currentLoggedInUserResourceAssembler;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        reply(response, OK,
                objectMapper.writeValueAsString(currentLoggedInUserResourceAssembler.toResource(authentication)));
    }

}
