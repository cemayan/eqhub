package org.dark.eqhub.postservice.adapter.redis;

import org.dark.eqhub.postservice.domain.model.Post;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class RedisRepository {
    private final ReactiveRedisOperations<String, Post> reactiveRedisOperations;

    public RedisRepository(ReactiveRedisOperations<String, Post> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    public Flux<Post> findAll(Long userId){
        return this.reactiveRedisOperations.opsForList().range(userId.toString(), 0, -1);
    }

    public Mono<Post> findById(Long userId, String id) {
        return this.findAll(userId).filter(p -> p.getId().equals(id)).last();
    }

    public Mono<Long> addToList(Long userId,Post post){
        return this.reactiveRedisOperations.opsForList().rightPush(userId.toString(), post);
    }

    public Mono<Boolean> add(String key, String hashKey, Object post){
        return this.reactiveRedisOperations.opsForHash().put(key, hashKey, post);
    }

    public Mono<Post> get(String key, String hashKey){
        return this.reactiveRedisOperations.opsForHash().get(key,hashKey).cast(Post.class);
    }


    public Mono<Boolean> deleteAll(Long userId) {
        return this.reactiveRedisOperations.opsForList().delete(userId.toString());
    }
}