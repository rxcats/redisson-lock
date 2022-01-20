package rxcats.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import rxcats.model.QTournamentGroupMember;
import rxcats.model.TournamentGroupMember;
import rxcats.repository.TournamentGroupMemberRepositorySupport;

import java.util.List;
import java.util.Optional;

public class TournamentGroupMemberRepositoryImpl extends QuerydslRepositorySupport
    implements TournamentGroupMemberRepositorySupport {

    private static final QTournamentGroupMember member = QTournamentGroupMember.tournamentGroupMember;

    public TournamentGroupMemberRepositoryImpl() {
        super(TournamentGroupMember.class);
    }

    @Override
    public List<TournamentGroupMember> findMembers(Long tid) {
        return from(member)
            .where(member.tid.eq(tid))
            .fetch();
    }

    @Override
    public long countMembers(Long tid) {
        return from(member)
            .where(member.tid.eq(tid))
            .fetchCount();
    }

    @Override
    public Optional<TournamentGroupMember> findMember(Long tid, Long uid) {
        return Optional.ofNullable(
            from(member)
                .where(member.tid.eq(tid).and(member.uid.eq(uid)))
                .fetchOne()
        );
    }

    @Override
    public void deleteByTid(Long tid) {
        delete(member)
            .where(member.tid.eq(tid))
            .execute();
    }

}
