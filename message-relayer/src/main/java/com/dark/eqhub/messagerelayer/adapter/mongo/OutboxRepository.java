package com.dark.eqhub.messagerelayer.adapter.mongo;


import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OutboxRepository extends ReactiveMongoRepository<Outbox, String> {

}
