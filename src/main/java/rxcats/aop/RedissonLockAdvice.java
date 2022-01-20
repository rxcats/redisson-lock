package rxcats.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.convert.DurationStyle;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE - 1) // @Transactional 보다 먼저 적용되도록...
@Aspect
public class RedissonLockAdvice {

    private final RedissonClient redissonClient;

    public RedissonLockAdvice(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Around("@annotation(RedissonLock)")
    public Object process(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();

        var annotation = signature.getMethod().getAnnotation(RedissonLock.class);

        String name = annotation.name();
        Duration timeout = DurationStyle.detectAndParse(annotation.timeout());

        RLock lock = redissonClient.getLock(name);

        if (lock.tryLock(timeout.toMillis(), TimeUnit.MILLISECONDS)) {
            try {
                return pjp.proceed();
            } catch (Throwable t) {
                log.info(t.getMessage(), t);
                throw t;
            } finally {
                lock.unlock();
            }
        } else {
            throw new IllegalStateException("redisson lock not available");
        }
    }
}
