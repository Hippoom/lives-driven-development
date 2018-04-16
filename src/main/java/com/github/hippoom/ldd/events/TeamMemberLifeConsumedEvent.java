package com.github.hippoom.ldd.events;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@RequiredArgsConstructor
public class TeamMemberLifeConsumedEvent {
    @JsonIgnore
    @NonNull
    private final String openId;
    @JsonIgnore
    private final int version;
    @NonNull
    private final String reason;
}
