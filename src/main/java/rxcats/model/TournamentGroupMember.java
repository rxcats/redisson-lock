package rxcats.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@ToString
@IdClass(TournamentGroupMemberPk.class)
@Entity
public class TournamentGroupMember implements Persistable<Long> {

    @Id
    @Getter
    @Setter
    private Long tid;

    @Id
    @Getter
    @Setter
    private Long uid;

    @Id
    @Getter
    @Setter
    private Integer groupId;

    private transient boolean isNew = false;

    public TournamentGroupMember() {

    }

    public TournamentGroupMember(TournamentGroupMemberPk pk) {
        this.tid = pk.getTid();
        this.groupId = pk.getGroupId();
        this.uid = pk.getUid();
    }

    public TournamentGroupMemberPk toPk() {
        return new TournamentGroupMemberPk(tid, groupId, uid);
    }

    public static TournamentGroupMember ofForceInsert() {
        var member = new TournamentGroupMember();
        member.isNew = true;
        return member;
    }

    public static TournamentGroupMember ofForceInsert(TournamentGroupMemberPk pk) {
        var member = new TournamentGroupMember(pk);
        member.isNew = true;
        return member;
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

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
