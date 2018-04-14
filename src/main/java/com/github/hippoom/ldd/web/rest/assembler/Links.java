package com.github.hippoom.ldd.web.rest.assembler;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.TemplateVariable.VariableType.REQUEST_PARAM;

public class Links {


    public static Link templateLink(String rel,
                                    ControllerLinkBuilder controllerLinkBuilder, List<String> queries, TemplateVariables templateVariables) {
        UriComponentsBuilder uriComponentsBuilder = controllerLinkBuilder
            .toUriComponentsBuilder();

        for (String query : queries) {
            uriComponentsBuilder = uriComponentsBuilder.replaceQuery(query);
        }

        return new Link(new UriTemplate(
            uriComponentsBuilder.build().toUriString().replace("%7B", "{").replace("%7D", "}"),
            templateVariables), rel);
    }

    public static Link templateLink(String rel,
                                    ControllerLinkBuilder controllerLinkBuilder, TemplateVariables templateVariables) {
        return templateLink(rel, controllerLinkBuilder, emptyList(), templateVariables);
    }

    public static TemplateVariables pagedTemplateVariables() {
        return pagedTemplateVariables(new TemplateVariable[] {});
    }

    public static TemplateVariables pagedTemplateVariables(TemplateVariable... others) {
        TemplateVariable[] pagedVariables = {
            new TemplateVariable("page", REQUEST_PARAM),
            new TemplateVariable("size", REQUEST_PARAM)
        };
        return new TemplateVariables(
            Stream.concat(Arrays.stream(pagedVariables), Arrays.stream(others)).collect(toList())
        );
    }
}
