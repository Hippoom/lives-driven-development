package com.github.hippoom.ldd.events;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
public class TeamMemberLifeConsumedEvent extends AbstractTeamMemberEvent {
    @NonNull
    private final String why;

    public TeamMemberLifeConsumedEvent(Long teamMemberId, int version, String reason) {
        super(teamMemberId, version);
        this.why = reason;
    }
}
