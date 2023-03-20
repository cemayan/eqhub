package org.dark.eqhub.postservice.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dark.eqhub.common.model.User;
import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.EventGrpcUsecase;
import org.dark.eqhub.postservice.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.domain.port.output.MongoPort;
import org.dark.eqhub.postservice.domain.port.output.RedisPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class PostServiceImpl implements PostUsecase {

    private final MongoPort mongoPort;
    private final RedisPort redisPort;

    private final EventGrpcUsecase eventGrpcUsecase;

    private final ObjectMapper objectMapper;


    public PostServiceImpl(MongoPort mongoPort, RedisPort redisPort, EventGrpcUsecase eventGrpcUsecase, ObjectMapper objectMapper) {
        this.mongoPort = mongoPort;
        this.redisPort = redisPort;
        this.eventGrpcUsecase = eventGrpcUsecase;
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Post> createPost(Post post) {
    return mongoPort.createPost(post).doOnNext(x-> {
        redisPort.put(Constants.CACHE_POSTS_KEY_NAME,x.getId(),x);

        eventGrpcUsecase.sendFriendListEvent().doOnNext(y -> {

            try {
                User user = objectMapper.readValue(y.toByteArray(), User.class);

                //TODO: Redis list rpush yapılacak.
                //redisPort.put(Constants.CACHE_FEEDS_KEY_NAME,user.getUserName(),);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    });
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return redisPort
                .get(Constants.CACHE_POSTS_KEY_NAME, postId)
                .cast(Post.class)
                .switchIfEmpty(
                        Mono.defer(() -> mongoPort.getPost(postId)
                                .doOnNext(x->  redisPort.put(Constants.CACHE_POSTS_KEY_NAME,x.getId(),x))));
    }
}

