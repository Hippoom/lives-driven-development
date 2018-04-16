package com.github.hippoom.ldd.web.security.support;

import com.github.hippoom.ldd.model.TeamMember;
import com.github.hippoom.ldd.model.TeamMemberRepository;
import com.github.hippoom.ldd.web.security.WeChatMiniAppAuthentication;
import com.github.hippoom.ldd.web.security.annotation.CurrentLoggedInUser;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class CurrentLoggedInUserHandlerMethodArgumentResolver
    implements HandlerMethodArgumentResolver {

    @NonNull
    private final TeamMemberRepository teamMemberRepository;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(TeamMember.class)
            && getCurrentLoggedInUserFrom(methodParameter) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            WeChatMiniAppAuthentication weChatMiniAppAuthentication = (WeChatMiniAppAuthentication) authentication;
            return teamMemberRepository.mustFindByOpenId(weChatMiniAppAuthentication.getOpenId());
        } else {
            //should never be here
            return null;
        }
    }

    private CurrentLoggedInUser getCurrentLoggedInUserFrom(MethodParameter methodParameter) {
        return methodParameter.getParameterAnnotation(CurrentLoggedInUser.class);
    }
}
