package org.dark.eqhub.postservice.domain.service;

import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.domain.port.output.MongoPort;
import org.dark.eqhub.postservice.domain.port.output.RedisPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostUsecase {

    private final MongoPort mongoPort;
    private final RedisPort redisPort;

    public PostServiceImpl(MongoPort mongoPort, RedisPort redisPort) {
        this.mongoPort = mongoPort;
        this.redisPort = redisPort;
    }

    @Override
    public Mono<Post> createPost(Post post) {
    return mongoPort.createPost(post).doOnNext(x-> {
        redisPort.put(Constants.CACHE_POSTS_KEY_NAME,x.getId(),x);
    });
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return redisPort.get(Constants.CACHE_POSTS_KEY_NAME, postId).switchIfEmpty(Mono.defer(() -> mongoPort.getPost(postId)));
    }
}

