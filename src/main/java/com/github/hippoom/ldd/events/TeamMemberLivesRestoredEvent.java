package com.github.hippoom.ldd.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class TeamMemberLivesRestoredEvent extends AbstractTeamMemberEvent {
    @NonNull
    private final String how;

    public TeamMemberLivesRestoredEvent(String openId, int version, String how) {
        super(openId, version);
        this.how = how;
    }
}
