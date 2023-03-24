package org.dark.eqhub.postservice.writeapi.adapter.redis;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class FeedsRedisRepository {

    private final ReactiveRedisOperations<String, String> reactiveRedisOperations;

    public FeedsRedisRepository(ReactiveRedisOperations<String, String> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }
    public Flux<String> findAll(String userId){
        return this.reactiveRedisOperations.opsForList().range(userId, 0, -1);
    }
    public Mono<Long> addToList(String userId,String postId){
        return this.reactiveRedisOperations.opsForList().rightPush(userId, postId);
    }
    public Mono<Boolean> deleteAll(String userId) {
        return this.reactiveRedisOperations.opsForList().delete(userId);
    }
}