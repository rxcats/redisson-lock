package rxcats.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@IdClass(TournamentGroupMemberPk.class)
@Entity
public class TournamentGroupMember {

    @Id
    private Long tid;

    @Id
    private Long uid;

    @Id
    private Integer groupId;

    public TournamentGroupMember(TournamentGroupMemberPk pk) {
        this.tid = pk.getTid();
        this.groupId = pk.getGroupId();
        this.uid = pk.getUid();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentGroupMember that = (TournamentGroupMember) o;
        return Objects.equals(tid, that.tid) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(uid, that.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, groupId, uid);
    }

}
