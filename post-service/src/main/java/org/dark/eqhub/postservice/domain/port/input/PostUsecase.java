package org.dark.eqhub.postservice.domain.port.input;

import org.dark.eqhub.postservice.domain.model.Post;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface PostUsecase {
    Mono<Post> CreatePost(Post post);
}
