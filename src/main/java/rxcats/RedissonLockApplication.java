package rxcats;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RedissonClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import rxcats.aop.RedissonLockAdvice;

@SpringBootApplication
public class RedissonLockApplication {

    @Bean
    public RedissonLockAdvice redissonLockAdvice(RedissonClient redissonClient) {
        return new RedissonLockAdvice(redissonClient);
    }

    @Bean
    public RestOperations restOperations(ObjectMapper objectMapper, MappingJackson2HttpMessageConverter converter) {
        RestTemplate restTemplate = new RestTemplate();
        converter.setObjectMapper(objectMapper);
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }


    public static void main(String[] args) {
        SpringApplication.run(RedissonLockApplication.class, args);
    }

}
