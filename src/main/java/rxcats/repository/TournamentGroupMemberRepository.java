package rxcats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rxcats.model.TournamentGroupMember;
import rxcats.model.TournamentGroupMemberPk;

public interface TournamentGroupMemberRepository extends JpaRepository<TournamentGroupMember, TournamentGroupMemberPk>,
    TournamentGroupMemberRepositorySupport {
}
