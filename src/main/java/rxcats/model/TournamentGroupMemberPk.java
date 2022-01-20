package rxcats.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TournamentGroupMemberPk implements Serializable {
    private Long tid;

    private Long uid;

    private Integer groupId;

    public TournamentGroupMemberPk(Long tid) {

    }

    public TournamentGroupMemberPk(Long tid, Integer groupId) {

    }

    public TournamentGroupMemberPk(Long tid, Integer groupId, Long uid) {
        this.tid = tid;
        this.groupId = groupId;
        this.uid = uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid, groupId, uid);
    }
}
