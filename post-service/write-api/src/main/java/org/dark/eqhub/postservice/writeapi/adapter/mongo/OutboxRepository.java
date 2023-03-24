package org.dark.eqhub.postservice.writeapi.adapter.mongo;

import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OutboxRepository extends ReactiveMongoRepository<Outbox, String> {

}
