package org.dark.eqhub.postservice.writeapi.domain.port.output;

import reactor.core.publisher.Mono;

public interface PostsRedisPort {
    void put(String key, String hashKey, Object data);
    Mono<Object> get(String key, String hashKey);
}
