package com.github.hippoom.ldd.web.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(NON_NULL)
@JsonIgnoreProperties({"authenticated", "detail", "authorities", "credentials", "principal", "name"})
public class WeChatMiniAppAuthentication extends AbstractAuthenticationToken {

    private String sessionKey;

    private String openId;

    private String unionId;

    public WeChatMiniAppAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public WeChatMiniAppAuthentication() {
        this(Collections.singleton(new SimpleGrantedAuthority("ROLE_DEFAULT")));
    }

    @Override
    public Object getCredentials() {
        return sessionKey;
    }

    @Override
    public Object getPrincipal() {
        return StringUtils.isEmpty(unionId) ? openId : unionId;
    }
}
