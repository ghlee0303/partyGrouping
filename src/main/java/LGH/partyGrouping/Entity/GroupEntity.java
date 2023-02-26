package LGH.partyGrouping.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group")
public class GroupEntity extends BaseEntity {
    @Id
    public Integer id;
    public String groupName;

    public GroupEntity() {
    }
}
