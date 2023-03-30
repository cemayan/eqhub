package org.dark.eqhub.postservice.writeapi.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.dark.eqhub.common.model.User;
import org.dark.eqhub.postservice.writeapi.application.constants.Constants;
import org.dark.eqhub.postservice.writeapi.application.util.Utils;
import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.dark.eqhub.postservice.writeapi.domain.port.input.EventGrpcUsecase;
import org.dark.eqhub.postservice.writeapi.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.writeapi.domain.port.output.FeedsRedisPort;
import org.dark.eqhub.postservice.writeapi.domain.port.output.KafkaPort;
import org.dark.eqhub.postservice.writeapi.domain.port.output.MongoPort;
import org.dark.eqhub.postservice.writeapi.domain.port.output.PostsRedisPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class PostServiceImpl implements PostUsecase {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final MongoPort mongoPort;
    private final PostsRedisPort postsRedisPort;
    private final FeedsRedisPort feedsRedisPort;
    private final EventGrpcUsecase eventGrpcUsecase;
    private final KafkaPort kafkaPort;
    private final ObjectMapper objectMapper;


    public PostServiceImpl(MongoPort mongoPort,
                           PostsRedisPort redisPort,
                           FeedsRedisPort feedsRedisPort,
                           EventGrpcUsecase eventGrpcUsecase,
                           KafkaPort kafkaPort, ObjectMapper objectMapper) {
        this.mongoPort = mongoPort;
        this.postsRedisPort = redisPort;
        this.feedsRedisPort = feedsRedisPort;
        this.eventGrpcUsecase = eventGrpcUsecase;
        this.kafkaPort = kafkaPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createOutboxEvent(Outbox outbox, String postId) {
        mongoPort.createOutboxEvent(outbox).doOnNext(x -> {
        }).subscribe();
    }

    @Override
    public Mono<Post> createPost(Post post) {
        return mongoPort.createPost(post).doOnNext(x -> {
            postsRedisPort.put(Constants.CACHE_POSTS_KEY_NAME, x.getId(), x);

            try {
                String s = objectMapper.writeValueAsString(post);
                createOutboxEvent(Utils.getNewPostCreatedEvent(s), x.getId());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        });
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return postsRedisPort
                .get(Constants.CACHE_POSTS_KEY_NAME, postId)
                .cast(Post.class)
                .switchIfEmpty(
                        Mono.defer(() -> mongoPort.getPost(postId)
                                .doOnNext(x -> postsRedisPort.put(Constants.CACHE_POSTS_KEY_NAME, x.getId(), x))));
    }

    @PostConstruct
    @Override
    public void consumeEvent() {

        //TODO: prevent multiple consume

        kafkaPort.consumeEvents().doOnNext(outbox -> {
            eventGrpcUsecase.sendFriendListEvent().doOnNext(y -> {

                try {
                    User user = objectMapper.readValue(y.toByteArray(), User.class);
                    Post post = objectMapper.readValue(y.getEventData().toByteArray(), Post.class);
                    feedsRedisPort.put(user.getUserName(), post.getId());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }).subscribe();

    }
}

