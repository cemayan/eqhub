package org.dark.eqhub.postservice.writeapi.domain.port.output;

public interface FeedsRedisPort {
    void put(String hashKey, String data);
}
