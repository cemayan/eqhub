package org.dark.eqhub.friendsservice.adapter.neo4j;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.dark.eqhub.friendsservice.domain.port.output.Neo4jPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Component
public class FriendsAdapter implements Neo4jPort {

    private static final Logger logger = LoggerFactory.getLogger(FriendsAdapter.class);
    private final FriendsRepository friendsRepository;

    public FriendsAdapter(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @Override
    public Flux<User> getFriends(String userName) {
        return friendsRepository.findByFollowers(userName);
    }

    @Override
    public void addUser(String userName) {
        friendsRepository.findByUserName(userName)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .doOnError(throwable -> logger.error("An error occurred when username getting to db.", throwable))
                .subscribe(optionalUser -> {
                    if (optionalUser.isEmpty()) {
                        friendsRepository.save(new User(userName)).subscribe();
                    }
                });
    }

    @Override
    public void addRelationToUser(String userName, String targetUserName) {
        friendsRepository.findByUserName(userName)
                .map(Optional::of)
                .defaultIfEmpty(Optional.empty())
                .doOnError(throwable -> logger.error("An error occurred when username getting to db.", throwable))
                .subscribe(optionalUser -> {
                    optionalUser.ifPresent(user -> friendsRepository.findByUserName(targetUserName)
                            .map(Optional::of)
                            .defaultIfEmpty(Optional.empty())
                            .subscribe(optionalUser2 -> {

                                if (optionalUser2.isPresent()) {

                                    user.follow(optionalUser2.get());
                                    friendsRepository.save(user).subscribe();
                                }
                            }));
                });
    }
}
