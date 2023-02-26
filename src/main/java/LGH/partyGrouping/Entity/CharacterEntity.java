package LGH.partyGrouping.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "character")
public class CharacterEntity extends BaseEntity {
    @Id
    private Integer id;

    private String name;

    private Integer level;

    public CharacterEntity() {
    }
}
