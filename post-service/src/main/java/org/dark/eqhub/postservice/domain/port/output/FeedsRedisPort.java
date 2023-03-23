package org.dark.eqhub.postservice.domain.port.output;

public interface FeedsRedisPort {
    void put(String hashKey, String data);
}
