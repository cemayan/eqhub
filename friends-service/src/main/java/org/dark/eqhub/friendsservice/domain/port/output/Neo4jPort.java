package org.dark.eqhub.friendsservice.domain.port.output;

import org.dark.eqhub.friendsservice.domain.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface Neo4jPort {
    Flux<User> GetFriends();
    Mono<User> AddRelationToUser(String userName, String targetUserName);
}
