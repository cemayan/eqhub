package org.dark.eqhub.postservice.domain.service;


import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.port.input.RedisUsecase;
import org.dark.eqhub.postservice.domain.port.output.PostsRedisPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class RedisServiceImpl implements RedisUsecase {

    private final PostsRedisPort redisPort;

    public RedisServiceImpl(PostsRedisPort redisPort) {
        this.redisPort = redisPort;
    }

    @Override
    public void Put(String hashKey, Object data) {
        redisPort.put(Constants.CACHE_POSTS_KEY_NAME,hashKey, data);
    }

    @Override
    public Mono<Object> Get(String hashKey) {
        return redisPort.get(Constants.CACHE_POSTS_KEY_NAME, hashKey);
    }
}
