package com.github.hippoom.ldd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Relation(collectionRelation = "teamMembers")
@Data
public class TeamMember {
    @Id
    @JsonIgnore
    private String openId;

    @Column
    private String displayName;

    @Column
    private int remainingLives;

    public void consumeLife() {
        this.remainingLives--;
    }
}
