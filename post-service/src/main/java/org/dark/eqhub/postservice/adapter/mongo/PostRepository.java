package org.dark.eqhub.postservice.adapter.mongo;

import org.dark.eqhub.postservice.domain.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

}
