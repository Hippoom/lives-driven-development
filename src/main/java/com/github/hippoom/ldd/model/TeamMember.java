package com.github.hippoom.ldd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.hateoas.core.Relation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table
@Relation(collectionRelation = "teamMembers")
@Data
public class TeamMember {
    public static final int DEFAULT_LIVES = 3;
    @Id
    private Long id;

    @Column
    @JsonIgnore
    private String openId;

    @Version
    private int version;

    @Column
    private String displayName;

    @Column
    private int remainingLives;

    public void consumeLife() {
        if (hasRemainingLives()) {
            this.remainingLives--;
        } else {
            throw new NotEnoughLivesException(this);
        }
    }

    public boolean hasRemainingLives() {
        return remainingLives > 0;
    }

    public void restoreLives() {
        this.remainingLives = DEFAULT_LIVES;
    }
}
