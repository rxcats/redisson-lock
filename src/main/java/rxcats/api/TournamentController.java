package rxcats.api;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rxcats.model.TournamentGroup;
import rxcats.model.TournamentGroupMember;
import rxcats.service.TournamentLockService;
import rxcats.service.TournamentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RequestMapping(path = "/tournament")
@RestController
public class TournamentController {

    private final TournamentService service;

    private final TournamentLockService lockService;

    @GetMapping("/groups")
    public Map<String, Object> getGroups() {
        List<TournamentGroup> groups = service.getGroups();
        return Map.of(
            "groups", groups
        );
    }

    @GetMapping("/groups/{tid}")
    public Map<String, Object> getGroups(@PathVariable(name = "tid") Long tid) {
        var response = new HashMap<String, Object>();

        Optional<TournamentGroup> group = service.getGroup(tid);

        if (group.isEmpty()) {
            response.put("group", null);
            response.put("members", List.of());
        } else {
            List<TournamentGroupMember> members = service.getGroupMembers(tid);
            response.put("group", group.get());
            response.put("members", members);
        }

        return response;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/groups/{tid}")
    public Map<String, Object> join(@PathVariable(name = "tid") Long tid, @RequestHeader("Authorization") Long uid) {
        Pair<TournamentGroup, TournamentGroupMember> result = lockService.joinGroupWithLock(tid, uid);
        return Map.of(
            "group", result.getFirst(),
            "member", result.getSecond()
        );
    }

    @DeleteMapping("/groups/{tid}")
    public Map<String, Object> delete(@PathVariable(name = "tid") Long tid) {
        service.deleteGroup(tid);
        return Map.of();
    }

}
