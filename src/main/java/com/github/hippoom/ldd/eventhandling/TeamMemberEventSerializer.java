package com.github.hippoom.ldd.eventhandling;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;

import static org.springframework.web.context.support.SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext;

public class TeamMemberEventSerializer extends JsonSerializer<String> {

    @Autowired
    private ObjectMapper objectMapper;

    public TeamMemberEventSerializer() {
        processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        HashMap json = objectMapper.readValue(value, HashMap.class);
        gen.writeObject(json);
    }
}
