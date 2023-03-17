package org.dark.eqhub.friendsservice.domain.port.output;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface Neo4jPort {
    Flux<User> getFriends(String userName);

    void addUser(String userName);
    void addRelationToUser(String userName, String targetUserName);
}
