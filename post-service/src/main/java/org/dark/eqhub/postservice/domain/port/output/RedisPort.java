package org.dark.eqhub.postservice.domain.port.output;

import reactor.core.publisher.Mono;

public interface RedisPort {
    void put(String key, String hashKey, Object data);
    Mono<Object> get(String key, String hashKey);
}
