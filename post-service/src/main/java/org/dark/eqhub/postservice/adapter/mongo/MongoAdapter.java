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
    public Mono<Post> CreatePost(Post post) {
        return postRepository.save(post);
    }
}
