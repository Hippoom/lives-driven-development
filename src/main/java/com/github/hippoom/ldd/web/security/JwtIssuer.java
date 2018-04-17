package com.github.hippoom.ldd.web.security;

import com.github.hippoom.ldd.time.Clock;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZonedDateTime;
import java.util.Date;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@RequiredArgsConstructor
@Component
@ConfigurationProperties(prefix = "security.jwt")
public class JwtIssuer {

    @Setter
    private String key;
    @Setter
    private int expireInMinutes = 30;

    @NonNull
    private final Clock clock;

    public String generateWith(WeChatMiniAppAuthentication authentication) {
        ZonedDateTime issuedAt = clock.nowWithZone();
        ZonedDateTime expiredAt = issuedAt.plusMinutes(expireInMinutes);
        return Jwts.builder()
                .setSubject(authentication.getOpenId())
                .setIssuer("Lives Driven Development")
                .setIssuedAt(Date.from(issuedAt.toInstant()))
                .setExpiration(Date.from(expiredAt.toInstant()))
                .signWith(HS512, key)
                .compact();
    }

    public WeChatMiniAppAuthentication verify(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt);
            ZonedDateTime now = clock.nowWithZone();
            ZonedDateTime expiration = clock.of(claims.getBody().getExpiration());
            if (now.isAfter(expiration)) {
                throw new JwtVerificationException("The token has been expired");// should I return expect and actual?
            }
            WeChatMiniAppAuthentication authentication = new WeChatMiniAppAuthentication();
            authentication.setOpenId(claims.getBody().getSubject());
            return authentication;
            //OK, we can trust this JWT
        } catch (Exception err) {
            throw new JwtVerificationException(format("Cannot verify jwt token due to %s", err.getMessage()), err);
        }
    }

    @ResponseStatus(code = UNAUTHORIZED)
    public static class JwtVerificationException extends AuthenticationException {
        public JwtVerificationException(String message) {
            super(message);
        }

        public JwtVerificationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
