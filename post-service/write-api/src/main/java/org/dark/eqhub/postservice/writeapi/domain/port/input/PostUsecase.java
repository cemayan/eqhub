package org.dark.eqhub.postservice.writeapi.domain.port.input;

import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface PostUsecase {

    void createOutboxEvent(Outbox outbox);
    Mono<Post> createPost(Post post);
    Mono<Post> getPost(String postId);
}
