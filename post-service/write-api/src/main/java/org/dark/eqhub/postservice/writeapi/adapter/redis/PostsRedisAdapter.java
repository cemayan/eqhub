package org.dark.eqhub.postservice.writeapi.adapter.redis;


import org.dark.eqhub.postservice.writeapi.domain.port.output.PostsRedisPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class PostsRedisAdapter implements PostsRedisPort {


    private final PostsRedisRepository redisRepository;

    public PostsRedisAdapter(PostsRedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    @Override
    public void put(String key, String hashKey, Object data) {
        redisRepository.add(key, hashKey, data).subscribe();
    }

    @Override
    public Mono<Object>  get(String key, String hashKey) {
        return redisRepository.get(key,hashKey);
    }
}
