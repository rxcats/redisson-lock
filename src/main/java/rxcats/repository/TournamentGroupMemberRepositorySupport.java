package rxcats.repository;

import rxcats.model.TournamentGroupMember;

import java.util.List;
import java.util.Optional;

public interface TournamentGroupMemberRepositorySupport {
    List<TournamentGroupMember> findMembers(Long tid);

    long countMembers(Long tid);

    Optional<TournamentGroupMember> findMember(Long tid, Long uid);

    void deleteByTid(Long tid);
}
