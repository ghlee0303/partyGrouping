package LGH.partyGrouping.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "party")
public class PartyEntity extends BaseEntity {
    @Id
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
}
