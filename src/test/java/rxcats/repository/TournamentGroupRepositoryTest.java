package rxcats.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import rxcats.model.TournamentGroup;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback(value = false)
@Transactional
@SpringBootTest
public class TournamentGroupRepositoryTest {

    @Autowired
    private TournamentGroupRepository repository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach(@Autowired JdbcOperations jdbcOperations) {
        jdbcOperations.execute("TRUNCATE TABLE `tournament_group`");
    }

    @Test
    public void forceInsert() {
        Optional<TournamentGroup> group = repository.findById(2L);
        assertThat(group).isEmpty();

        TournamentGroup tournamentGroup = TournamentGroup.ofForceInsert(2L);
        tournamentGroup.initialize();

        TournamentGroup inserted = repository.save(tournamentGroup);
        assertThat(entityManager.contains(tournamentGroup)).isTrue();

        Optional<TournamentGroup> find = repository.findById(2L);
        assertThat(find).isPresent();
        assertThat(inserted).isSameAs(inserted);
    }

}
