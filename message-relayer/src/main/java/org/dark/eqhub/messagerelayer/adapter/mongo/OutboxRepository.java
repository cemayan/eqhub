package org.dark.eqhub.messagerelayer.adapter.mongo;


import org.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OutboxRepository extends ReactiveMongoRepository<Outbox, String> {

}
