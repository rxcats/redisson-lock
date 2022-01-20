package rxcats.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class TournamentGroup {

    @Id
    private Long tid;

    private Integer groupId;

    private Integer userCount;

    private Integer totalUserCount;

    public boolean isFullGroup(Integer maxUser) {
        return userCount + 1 > maxUser;
    }

    public void initialize() {
        groupId = 1;
        userCount = 1;
        totalUserCount = 1;
    }

    public void addGroup() {
        this.groupId++;
        this.userCount = 1;
    }

    public void addUser() {
        this.userCount++;
    }

    public void incrTotalUserCount() {
        this.totalUserCount++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TournamentGroup that = (TournamentGroup) o;
        return Objects.equals(tid, that.tid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tid);
    }

}
