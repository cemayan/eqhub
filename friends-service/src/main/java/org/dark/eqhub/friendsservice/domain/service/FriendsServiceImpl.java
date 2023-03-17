package org.dark.eqhub.friendsservice.domain.service;

import org.dark.eqhub.friendsservice.domain.model.User;
import org.dark.eqhub.friendsservice.domain.port.input.FriendsUsecase;
import org.dark.eqhub.friendsservice.domain.port.output.Neo4jPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FriendsServiceImpl implements FriendsUsecase {

    private final Neo4jPort neo4jPort;

    public FriendsServiceImpl(Neo4jPort neo4jPort) {
        this.neo4jPort = neo4jPort;
    }


    @Override
    public Flux<User> getFriends(String userName) {
        return  neo4jPort.getFriends(userName);
    }

    @Override
    public void addUser(String userName) {
        neo4jPort.addUser(userName);
    }

    @Override
    public void addRelationToUser(String userName, String targetUserName) {
        neo4jPort.addRelationToUser(userName,targetUserName);
    }
}
