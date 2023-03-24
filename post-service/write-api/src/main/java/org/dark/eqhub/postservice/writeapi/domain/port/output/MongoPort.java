package org.dark.eqhub.postservice.writeapi.domain.port.output;


import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import reactor.core.publisher.Mono;

public interface MongoPort {
    Mono<Post> createPost(Post post);
    Mono<Post> getPost(String postId);

    Mono<Outbox> createOutboxEvent(Outbox outbox);
}
