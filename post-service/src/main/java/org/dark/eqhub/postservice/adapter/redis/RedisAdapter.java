package org.dark.eqhub.postservice.adapter.redis;


import org.dark.eqhub.postservice.domain.port.output.RedisPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class RedisAdapter implements RedisPort {


    private final RedisRepository redisRepository;

    public RedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void Put(String key, String hashKey, Object data) {
        Mono<Boolean> add = redisRepository.add(key, hashKey, data);
        add.subscribe();
    }

    @Override
    public Optional<String> Get(String key) {
        return Optional.empty();
    }
}
