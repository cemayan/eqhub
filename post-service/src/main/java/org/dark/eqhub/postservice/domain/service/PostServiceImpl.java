package org.dark.eqhub.postservice.domain.service;

import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.domain.port.output.MongoPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostUsecase {

    private final MongoPort mongoPort;

    public PostServiceImpl(MongoPort mongoPort) {
        this.mongoPort = mongoPort;
    }

    @Override
    public Mono<Post> CreatePost(Post post) {
        return mongoPort.CreatePost(post);
    }
}

