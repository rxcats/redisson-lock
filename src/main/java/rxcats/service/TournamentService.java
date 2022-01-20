package rxcats.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rxcats.model.TournamentGroup;
import rxcats.model.TournamentGroupMember;
import rxcats.model.TournamentGroupMemberPk;
import rxcats.repository.TournamentGroupMemberRepository;
import rxcats.repository.TournamentGroupRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@AllArgsConstructor
@Service
public class TournamentService {
    private static final int MAX_USER = 50;

    private final TournamentGroupRepository tournamentGroupRepository;
    private final TournamentGroupMemberRepository tournamentGroupMemberRepository;

    public Optional<TournamentGroup> getGroup(Long tid) {
        return tournamentGroupRepository.findById(tid);
    }

    public List<TournamentGroup> getGroups() {
        return tournamentGroupRepository.findGroups();
    }

    public long countGroups() {
        return tournamentGroupRepository.countGroups();
    }

    public TournamentGroup saveGroup(Long tid) {
        Optional<TournamentGroup> group = tournamentGroupRepository.findById(tid);

        if (group.isEmpty()) {
            var newGroup = new TournamentGroup();
            newGroup.setTid(tid);
            newGroup.initialize();

            tournamentGroupRepository.save(newGroup);

            return newGroup;
        } else {
            if (group.get().isFullGroup(MAX_USER)) {
                group.get().addGroup();
            } else {
                group.get().addUser();
            }

            group.get().incrTotalUserCount();

            tournamentGroupRepository.save(group.get());

            return group.get();
        }

    }

    public TournamentGroupMember addGroupMember(TournamentGroup group, Long uid) {
        var pk = new TournamentGroupMemberPk(group.getTid(), group.getGroupId(), uid);
        var member = new TournamentGroupMember(pk);
        return tournamentGroupMemberRepository.save(member);
    }

    public Optional<TournamentGroupMember> getGroupMember(Long tid, Long uid) {
        return tournamentGroupMemberRepository.findMember(tid, uid);
    }

    public List<TournamentGroupMember> getGroupMembers(Long tid) {
        return tournamentGroupMemberRepository.findMembers(tid);
    }

    public long countGroupMembers(Long tid) {
        return tournamentGroupMemberRepository.countMembers(tid);
    }

    public void deleteGroup(Long tid) {
        tournamentGroupRepository.deleteById(tid);
        tournamentGroupMemberRepository.deleteByTid(tid);
    }

}
