package org.dark.eqhub.friendsservice.adapter.neo4j;


import org.dark.eqhub.friendsservice.domain.model.User;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;


public interface FriendsRepository extends ReactiveNeo4jRepository<User, Long> {
}
