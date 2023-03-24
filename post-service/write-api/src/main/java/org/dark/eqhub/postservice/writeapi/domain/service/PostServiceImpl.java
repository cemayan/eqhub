package org.dark.eqhub.postservice.writeapi.domain.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dark.eqhub.common.model.User;
import org.dark.eqhub.postservice.writeapi.application.constants.Constants;
import org.dark.eqhub.postservice.writeapi.application.util.Utils;
import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.dark.eqhub.postservice.writeapi.domain.port.input.EventGrpcUsecase;
import org.dark.eqhub.postservice.writeapi.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.writeapi.domain.port.output.FeedsRedisPort;
import org.dark.eqhub.postservice.writeapi.domain.port.output.MongoPort;
import org.dark.eqhub.postservice.writeapi.domain.port.output.PostsRedisPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class PostServiceImpl implements PostUsecase {

    private final MongoPort mongoPort;
    private final PostsRedisPort postsRedisPort;
    private final FeedsRedisPort feedsRedisPort;

    private final EventGrpcUsecase eventGrpcUsecase;
    private final ObjectMapper objectMapper;


    public PostServiceImpl(MongoPort mongoPort,
                           PostsRedisPort redisPort,
                           FeedsRedisPort feedsRedisPort,
                           EventGrpcUsecase eventGrpcUsecase,
                           ObjectMapper objectMapper) {
        this.mongoPort = mongoPort;
        this.postsRedisPort = redisPort;
        this.feedsRedisPort = feedsRedisPort;
        this.eventGrpcUsecase = eventGrpcUsecase;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createOutboxEvent(Outbox outbox, String postId) {
        mongoPort.createOutboxEvent(outbox).doOnNext(x-> {
            eventGrpcUsecase.sendFriendListEvent().doOnNext(y -> {

                try {
                    User user = objectMapper.readValue(y.toByteArray(), User.class);
                    feedsRedisPort.put(user.getUserName(),postId);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        });
    }

    @Override
    public Mono<Post> createPost(Post post) {
    return mongoPort.createPost(post).doOnNext(x-> {
        postsRedisPort.put(Constants.CACHE_POSTS_KEY_NAME,x.getId(),x);
        createOutboxEvent(Utils.getNewPostCreatedEvent(), x.getId());
    });
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return postsRedisPort
                .get(Constants.CACHE_POSTS_KEY_NAME, postId)
                .cast(Post.class)
                .switchIfEmpty(
                        Mono.defer(() -> mongoPort.getPost(postId)
                                .doOnNext(x->  postsRedisPort.put(Constants.CACHE_POSTS_KEY_NAME,x.getId(),x))));
    }
}

