package org.dark.eqhub.postservice.writeapi.adapter.mongo;

import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

}
