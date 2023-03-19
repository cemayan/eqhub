package org.dark.eqhub.postservice.domain.port.output;

import java.util.Optional;

public interface RedisPort {
    void Put(String key, String hashKey, Object data);
    Optional<String> Get(String key);
}
