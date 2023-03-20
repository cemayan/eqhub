package org.dark.eqhub.postservice.adapter.redis;


import org.dark.eqhub.postservice.domain.port.output.RedisPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class RedisAdapter implements RedisPort {


    private final RedisRepository redisRepository;

    public RedisAdapter(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void put(String key, String hashKey, Object data) {
        Mono<Boolean> add = redisRepository.add(key, hashKey, data);
        add.subscribe();
    }

    @Override
    public Mono<Object>  get(String key, String hashKey) {
        return redisRepository.get(key,hashKey);
    }
}
