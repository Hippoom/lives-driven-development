package com.github.hippoom.ldd.web.security;

import io.jsonwebtoken.Claims;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationProcessingFilter
        extends AbstractAuthenticationProcessingFilter {
    @Setter
    private JwtIssuer jwtIssuer;

    public JwtAuthenticationProcessingFilter(
            RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException {

        return jwtIssuer.verify(getJwt(request));
    }

    private String getJwt(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) {
            throw new BadCredentialsException("Missing Authorization header");
        }
        return authorization.replace("Bearer ", "");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
}
