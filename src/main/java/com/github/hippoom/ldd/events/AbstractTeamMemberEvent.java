package com.github.hippoom.ldd.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class AbstractTeamMemberEvent {
    @JsonIgnore
    @NonNull
    private final String openId;
    @JsonIgnore
    private final int version;

    public AbstractTeamMemberEvent(String openId, int version) {
        this.openId = openId;
        this.version = version;
    }

    public String getOpenId() {
        return this.openId;
    }

    public int getVersion() {
        return this.version;
    }
}
