package org.dark.eqhub.messagerelayer.domain.port.output;

import org.dark.eqhub.messagerelayer.domain.model.Outbox;
import reactor.core.publisher.Flux;

public interface MongoPort {
    void removeOutboxEvent(String id);

    Flux<Outbox> getAllOutboxEvents();
}
