package com.github.hippoom.ldd.web.security;

import com.google.common.collect.ImmutableMap;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@NoArgsConstructor(access = PRIVATE)
public final class HttpResponseRenderer {

    public static void reply(HttpServletResponse response, HttpStatus statusCode,
                             String responseBody)
            throws IOException {
        reply(response, statusCode, ImmutableMap.of(), responseBody);
    }

    public static void reply(HttpServletResponse response, HttpStatus statusCode,
                             Map<String, String> headers,
                             String responseBody)
            throws IOException {
        response.setStatus(statusCode.value());
        response.setHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
        headers.forEach(response::setHeader);
        response.getWriter().println(responseBody);
    }
}
