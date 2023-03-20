package org.dark.eqhub.postservice.adapter.mongo;

import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.output.MongoPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MongoAdapter implements MongoPort {

    private final PostRepository postRepository;

    public MongoAdapter(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Mono<Post> createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Mono<Post> getPost(String postId) {
        return postRepository.findById(postId);
    }
}
