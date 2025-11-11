package com.example.memberservice.common.redis.service;


import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenRedisService implements RedisSingleDataService {

    private final RedisTemplate<String, Object> redisTemplate;

    //    @Value("")
    //TODO(JWT 관련 설정이후 Value로 받아오게 수정)
    private long refreshTokenTTL = 360000;

    @Override
    public int setSingleData(String key, Object value) {
        Duration duration = Duration.ofMillis(refreshTokenTTL);

        return this.executeOperation(() -> valueOperations().set(key, value, duration));
    }

    @Override
    public Optional<String> getSingleData(String key) {
        Object value = valueOperations().get(key);

        return Optional.ofNullable(value).map(Object::toString);
    }

    @Override
    public boolean deleteSingleData(String key) {

        Boolean result = redisTemplate.delete(key);

        return Boolean.TRUE.equals(result);
    }

    private ValueOperations<String, Object> valueOperations() {
        return redisTemplate.opsForValue();
    }

    private ListOperations<String, Object> listOperations() {
        return redisTemplate.opsForList();
    }


    private int executeOperation(Runnable operation) {
        try {
            operation.run();
            return 1;
        } catch (Exception e) {
            System.out.println("Redis 작업 오류 발생 :: " + e.getMessage());
            return 0;
        }
    }
}
