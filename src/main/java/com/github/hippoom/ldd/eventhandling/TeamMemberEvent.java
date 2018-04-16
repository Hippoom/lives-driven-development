package com.github.hippoom.ldd.eventhandling;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Accessors(chain = true)
public class TeamMemberEvent {
    @Id
    private Long sequence;
    /**
     * TeamMember.version
     */
    @Column
    private int version;
    @Column
    private String openId;
    @Column
    private String payload;
    @Column(name = "AT")
    private LocalDateTime when;
}
