package rxcats.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import rxcats.model.TournamentGroupMember;
import rxcats.model.TournamentGroupMemberPk;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Rollback(value = false)
@Transactional
@SpringBootTest
public class TournamentGroupMemberRepositoryTest {

    @Autowired
    private TournamentGroupMemberRepository repository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    void beforeEach(@Autowired JdbcOperations jdbcOperations) {
        jdbcOperations.execute("TRUNCATE TABLE `tournament_group_member`");
    }

    @Test
    public void forceInsert() {
        Optional<TournamentGroupMember> member = repository.findMember(2L, 100L);
        assertThat(member).isEmpty();

        TournamentGroupMemberPk pk = new TournamentGroupMemberPk(2L, 1, 100L);

        TournamentGroupMember tgm = TournamentGroupMember.ofForceInsert(pk);

        TournamentGroupMember inserted = repository.save(tgm);
        assertThat(entityManager.contains(inserted)).isTrue();

        Optional<TournamentGroupMember> find = repository.findById(pk);
        assertThat(find).isPresent();
        assertThat(inserted).isSameAs(inserted);
    }
}
