package com.party_grouping.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "party_table")
public class PartyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String partyName;
    public LocalDateTime entryTime;

    @ManyToOne(targetEntity = DungeonEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name="dungeon_id")
    public DungeonEntity dungeon;

    @ManyToOne(targetEntity = GroupEntity.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    public GroupEntity group;

    public PartyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getPartyName() {
        return partyName;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public DungeonEntity getDungeon() {
        return dungeon;
    }

    public GroupEntity getGroup() {
        return group;
    }
}
