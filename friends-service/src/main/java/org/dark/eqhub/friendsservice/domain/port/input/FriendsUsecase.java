package org.dark.eqhub.friendsservice.domain.port.input;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public interface FriendsUsecase {
    Flux<User> getFriends(String userName);

    void addUser(String userName);
    void addRelationToUser(String userName, String targetUserName);
}
