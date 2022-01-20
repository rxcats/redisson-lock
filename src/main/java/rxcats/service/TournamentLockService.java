package rxcats.service;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rxcats.aop.RedissonLock;
import rxcats.model.TournamentGroup;
import rxcats.model.TournamentGroupMember;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TournamentLockService {

    private final TournamentService tournamentService;

    @RedissonLock(name = "TournamentGroup")
    @Transactional
    public Pair<TournamentGroup, TournamentGroupMember> joinGroupWithLock(Long tid, Long uid) {
        Optional<TournamentGroupMember> member = tournamentService.getGroupMember(tid, uid);
        if (member.isPresent()) {
            Optional<TournamentGroup> group = tournamentService.getGroup(tid);
            return Pair.of(group.orElseThrow(), member.get());
        }

        TournamentGroup g = tournamentService.saveGroup(tid);
        TournamentGroupMember m = tournamentService.addGroupMember(g, uid);
        return Pair.of(g, m);
    }

    @Transactional
    public Pair<TournamentGroup, TournamentGroupMember> joinGroupWithNoLock(Long tid, Long uid) {
        Optional<TournamentGroupMember> member = tournamentService.getGroupMember(tid, uid);
        if (member.isPresent()) {
            Optional<TournamentGroup> group = tournamentService.getGroup(tid);
            return Pair.of(group.orElseThrow(), member.get());
        }

        TournamentGroup g = tournamentService.saveGroup(tid);
        TournamentGroupMember m = tournamentService.addGroupMember(g, uid);
        return Pair.of(g, m);
    }
}
