package rxcats.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@ToString
@Entity
public class TournamentGroup implements Persistable<Long> {

    @Id
    @Getter
    @Setter
    private Long tid;

    @Getter
    @Setter
    private Integer groupId;

    @Getter
    @Setter
    private Integer userCount;

    @Getter
    @Setter
    private Integer totalUserCount;

    private transient boolean isNew = false;

    public TournamentGroup() {

    }

    public TournamentGroup(Long tid) {
        this.tid = tid;
    }

    public static TournamentGroup ofForceInsert(Long tid) {
        var tg = new TournamentGroup(tid);
        tg.isNew = true;
        return tg;
    }

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

    @Override
    public Long getId() {
        return tid;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
