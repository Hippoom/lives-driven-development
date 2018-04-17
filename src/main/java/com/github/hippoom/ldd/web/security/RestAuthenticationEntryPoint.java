package com.github.hippoom.ldd.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.github.hippoom.ldd.web.security.HttpResponseRenderer.reply;
import static org.springframework.hateoas.mvc.BasicLinkBuilder.linkToCurrentMapping;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Slf4j
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, LogoutSuccessHandler, AuthenticationFailureHandler {

    @NonNull
    private final ObjectMapper objectMapper;

    private void doReply(HttpServletResponse response)
            throws IOException {
        Resource<String> resource = new Resource<>("");
        resource.add(linkToCurrentMapping().slash("/loginViaWeChatMiniApp").withRel("loginViaWeChatMiniApp"));
        String responseBody = objectMapper.writeValueAsString(resource);
        reply(response, UNAUTHORIZED, responseBody);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        doReply(response);
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.debug(authException.getMessage(), authException);
        doReply(response);
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException {
        doReply(response);
    }
}
