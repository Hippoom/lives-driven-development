package com.github.hippoom.ldd.web.security

import org.springframework.security.test.context.support.WithSecurityContext

import java.lang.annotation.Documented
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.Target

import static java.lang.annotation.ElementType.TYPE
import static java.lang.annotation.RetentionPolicy.RUNTIME

@Target(TYPE)
@Retention(RUNTIME)
@Inherited
@Documented
@WithSecurityContext(factory = IllidanStormrage.class)
@interface WithIllidanStormrage {

}