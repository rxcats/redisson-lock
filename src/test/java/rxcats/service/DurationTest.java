package rxcats.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.convert.DurationStyle;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DurationTest {

    @Test
    void duration() {
        Duration duration = DurationStyle.detectAndParse("1s");
        assertThat(duration).isEqualTo(Duration.ofSeconds(1));
    }

}
