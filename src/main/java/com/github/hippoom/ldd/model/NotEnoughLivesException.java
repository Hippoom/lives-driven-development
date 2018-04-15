package com.github.hippoom.ldd.model;

import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@ResponseStatus(PRECONDITION_FAILED)
public class NotEnoughLivesException extends RuntimeException {
    public NotEnoughLivesException(TeamMember teamMember) {
        super(format("Trying to consume lives for %s, but not enough lives", teamMember.getDisplayName()));
    }
}
