package rxcats.repository.impl;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import rxcats.model.QTournamentGroup;
import rxcats.model.TournamentGroup;
import rxcats.repository.TournamentGroupRepositorySupport;

import java.util.List;

public class TournamentGroupRepositoryImpl extends QuerydslRepositorySupport
    implements TournamentGroupRepositorySupport {

    private static final QTournamentGroup group = QTournamentGroup.tournamentGroup;

    public TournamentGroupRepositoryImpl() {
        super(TournamentGroup.class);
    }

    @Override
    public List<TournamentGroup> findGroups() {
        return from(group)
            .fetch();
    }

    @Override
    public long countGroups() {
        return from(group)
            .fetchCount();
    }
}
