package rxcats.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.jdbc.core.JdbcOperations;
import rxcats.model.TournamentGroup;
import rxcats.model.TournamentGroupMember;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.stream.LongStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Slf4j
@SpringBootTest
class TournamentServiceTest {

    @Autowired
    private TournamentLockService tournamentLockService;

    @Autowired
    private TournamentService tournamentService;

    @BeforeEach
    void beforeEach(@Autowired JdbcOperations jdbcOperations) {
        jdbcOperations.execute("TRUNCATE TABLE `tournament_group`");
        jdbcOperations.execute("TRUNCATE TABLE `tournament_group_member`");
    }

    @Test
    void joinGroupWithLock() {
        Long tid = System.currentTimeMillis();
        Pair<TournamentGroup, TournamentGroupMember> result = this.tournamentLockService.joinGroupWithLock(tid, 1L);
        log.info("group:{}, member:{}", result.getFirst(), result.getSecond());
    }

    @Test
    void joinGroupParallelWithLock() {
        Long tid = System.currentTimeMillis();

        LongStream.rangeClosed(1, 111)
            .parallel()
            .forEach(uid -> {
                Pair<TournamentGroup, TournamentGroupMember> result = this.tournamentLockService.joinGroupWithLock(tid, uid);
                log.info("group:{}, member:{}", result.getFirst(), result.getSecond());
            });

        long count = tournamentService.countGroupMembers(tid);

        assertThat(count).isEqualTo(111);
    }

    @Test
    void joinGroupParallelWithNoLock() {
        Long tid = System.currentTimeMillis();

        assertThatThrownBy(() -> {
            LongStream.rangeClosed(1, 111)
                .parallel()
                .forEach(uid -> {
                    Pair<TournamentGroup, TournamentGroupMember> result = this.tournamentLockService.joinGroupWithNoLock(tid, uid);
                    log.info("group:{}, member:{}", result.getFirst(), result.getSecond());
                });
        }).isInstanceOf(DataIntegrityViolationException.class);
    }
}
