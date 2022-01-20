package rxcats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rxcats.model.TournamentGroup;

public interface TournamentGroupRepository extends JpaRepository<TournamentGroup, Long>,
    TournamentGroupRepositorySupport {
}
