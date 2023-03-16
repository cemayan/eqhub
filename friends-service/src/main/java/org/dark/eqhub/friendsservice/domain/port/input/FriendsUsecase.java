package org.dark.eqhub.friendsservice.domain.port.input;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface FriendsUsecase {
    Flux<User> GetFriends();
    Mono<User> AddRelationToUser(String userName, String targetUserName);
}
