package com.dark.eqhub.messagerelayer.domain.port.output;

import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import reactor.core.publisher.Flux;

public interface MongoPort {
    Flux<Outbox> getAllOutboxEvents();
}
