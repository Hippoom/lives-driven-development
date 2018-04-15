package com.github.hippoom.ldd.web.security.support;

import com.github.hippoom.ldd.web.security.WeChatMiniAppAuthentication;
import com.github.hippoom.ldd.web.security.annotation.CurrentLoggedInUser;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@ControllerAdvice
public class InjectCurrentLoggedInUserRequestBodyAdvice extends RequestBodyAdviceAdapter {
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        final Annotation[] parameterAnnotations = methodParameter.getParameterAnnotations();
        for (final Annotation annotation : parameterAnnotations) {
            if (annotation.annotationType().equals(CurrentLoggedInUser.class)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object afterBodyRead(final Object body,
                                final HttpInputMessage inputMessage,
                                final MethodParameter parameter,
                                final Type targetType,
                                final Class<? extends HttpMessageConverter<?>> converterType) {

        final Object obj = super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            WeChatMiniAppAuthentication weChatMiniAppAuthentication = (WeChatMiniAppAuthentication) authentication;
            if (obj instanceof CurrentLoggedInUserAware) {
                ((CurrentLoggedInUserAware) obj).setCurrentLoggedInUser(weChatMiniAppAuthentication.getOpenId());
            }
        } else {
            //should never be here
            return null;
        }
        return obj;
    }

}
