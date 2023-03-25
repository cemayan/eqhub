package org.dark.eqhub.postservice.writeapi.adapter.mongo;

import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.dark.eqhub.postservice.writeapi.domain.port.output.MongoPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MongoAdapter implements MongoPort {

    private static final Logger logger = LoggerFactory.getLogger(MongoAdapter.class);

    private final PostRepository postRepository;
    private final OutboxRepository outboxRepository;

    public MongoAdapter(PostRepository postRepository, OutboxRepository outboxRepository) {
        this.postRepository = postRepository;
        this.outboxRepository = outboxRepository;
    }

    @Override
    public Mono<Post> createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Mono<Outbox> createOutboxEvent(Outbox outbox) {
        return outboxRepository.save(outbox);
    }
}
