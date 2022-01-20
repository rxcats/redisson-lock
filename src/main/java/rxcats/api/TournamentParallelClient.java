package rxcats.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import java.util.stream.LongStream;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/client")
public class TournamentParallelClient {

    private final RestOperations restOperations;

    @PostMapping("/parallel")
    public String parallelJob(@RequestParam(name = "count", defaultValue = "100") Integer count) {
        long tid = System.currentTimeMillis();

        LongStream.rangeClosed(91001, count + 91000).parallel().forEach(uid -> {
            var headers = new HttpHeaders();
            var request = new HttpEntity<String>(headers);
            headers.add("Authorization", String.valueOf(uid));
            restOperations
                .exchange("http://localhost:8080/tournament/groups/" + tid, HttpMethod.POST, request, String.class);
        });

        var headers = new HttpHeaders();
        var request = new HttpEntity<String>(headers);
        headers.add("Authorization", "100");
        ResponseEntity<String> exchange = restOperations
            .exchange("http://localhost:8080/tournament/groups/" + tid, HttpMethod.GET, request, String.class);
        return exchange.getBody();
    }

}
