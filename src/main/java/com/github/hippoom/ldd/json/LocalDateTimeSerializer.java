package com.github.hippoom.ldd.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.hippoom.ldd.time.Clock;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Autowired
    private Clock clock;

    public LocalDateTimeSerializer() {
        processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(clock.toEpochSecond(value));
    }
}
