package org.dark.eqhub.postservice.domain.port.output;


import org.dark.eqhub.postservice.domain.model.Post;
import reactor.core.publisher.Mono;

public interface MongoPort {
    Mono<Post> createPost(Post post);
    Mono<Post> getPost(String postId);
}
