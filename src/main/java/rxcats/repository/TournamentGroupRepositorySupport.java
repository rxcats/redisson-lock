package rxcats.repository;

import rxcats.model.TournamentGroup;

import java.util.List;

public interface TournamentGroupRepositorySupport {
    List<TournamentGroup> findGroups();

    long countGroups();
}
