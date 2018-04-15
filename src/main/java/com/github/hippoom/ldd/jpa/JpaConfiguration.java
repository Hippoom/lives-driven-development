package com.github.hippoom.ldd.jpa;

import com.github.hippoom.ldd.Application;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackageClasses = {Application.class, Jsr310JpaConverters.class})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.github.hippoom.ldd.jpa"})
@Configuration
public class JpaConfiguration {

}
