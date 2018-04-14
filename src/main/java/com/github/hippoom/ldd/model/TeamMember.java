package com.github.hippoom.ldd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

@Relation(collectionRelation = "teamMembers")
@Data
public class TeamMember {
    @JsonIgnore
    private String openId;
    private String displayName;
}
