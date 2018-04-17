package com.github.hippoom.ldd.eventhandling;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.hippoom.ldd.json.LocalDateTimeSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
@Data
@Accessors(chain = true)
@Relation(collectionRelation = "events")
public class TeamMemberEvent {
    @JsonIgnore
    @Id
    private Long sequence;
    /**
     * TeamMember.version
     */
    @JsonIgnore
    @Column
    private int version;
    @JsonIgnore
    @Column
    private Long teamMemberId;

    @Column(name = "EVENT_TYPE")
    private String type;

    @JsonSerialize(using = TeamMemberEventSerializer.class)
    @Column
    private String payload;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Column(name = "AT")
    private LocalDateTime when;
}
