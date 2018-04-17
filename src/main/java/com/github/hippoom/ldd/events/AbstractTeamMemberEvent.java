package com.github.hippoom.ldd.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class AbstractTeamMemberEvent {
    @JsonIgnore
    @NonNull
    private final Long teamMemberId;
    @JsonIgnore
    private final int version;

    private final String type;

    public AbstractTeamMemberEvent(Long teamMemberId, int version, String type) {
        this.teamMemberId = teamMemberId;
        this.version = version;
        this.type = type;
    }

}
