package org.dark.eqhub.postservice.writeapi.domain.port.output;

import org.dark.eqhub.postservice.writeapi.domain.model.Outbox;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface KafkaPort {

    Flux<Outbox> consumeEvents();

}
