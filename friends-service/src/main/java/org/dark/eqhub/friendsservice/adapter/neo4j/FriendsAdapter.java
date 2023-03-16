package org.dark.eqhub.friendsservice.adapter.neo4j;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.dark.eqhub.friendsservice.domain.port.output.Neo4jPort;
import org.springframework.security.core.context.SecurityContextHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FriendsAdapter implements Neo4jPort {

    private final FriendsRepository friendsRepository;

    public FriendsAdapter(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @Override
    public Flux<User> GetFriends() {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return null;
    }

    @Override
    public Mono<User> AddRelationToUser(String userName, String targetUserName) {
        return null;
    }
}
