package com.dark.eqhub.messagerelayer.domain.port.output;

import com.dark.eqhub.messagerelayer.domain.model.Outbox;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

public interface KafkaPort {

    Mono<SenderResult<Void>> SendEvent(Outbox outbox);
}
