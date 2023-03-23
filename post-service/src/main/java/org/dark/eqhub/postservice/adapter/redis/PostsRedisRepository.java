package org.dark.eqhub.postservice.adapter.redis;

import org.dark.eqhub.postservice.domain.model.Post;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PostsRedisRepository {
    private final ReactiveRedisOperations<String, Post> reactiveRedisOperations;

    public PostsRedisRepository(ReactiveRedisOperations<String, Post> reactiveRedisOperations) {
        this.reactiveRedisOperations = reactiveRedisOperations;
    }

    public Flux<Post> findAll(String userId){
        return this.reactiveRedisOperations.opsForList().range(userId, 0, -1);
    }

    public Mono<Post> findById(String userId, String id) {
        return this.findAll(userId).filter(p -> p.getId().equals(id)).last();
    }

    public Mono<Long> addToList(String userId,Post post){
        return this.reactiveRedisOperations.opsForList().rightPush(userId, post);
    }

    public Mono<Boolean> add(String key, String hashKey, Object post){
        return this.reactiveRedisOperations.opsForHash().put(key, hashKey, post);
    }

    public Mono<Object> get(String key, String hashKey){
        return this.reactiveRedisOperations.opsForHash().get(key,hashKey);
    }


    public Mono<Boolean> deleteAll(String userId) {
        return this.reactiveRedisOperations.opsForList().delete(userId);
    }
}