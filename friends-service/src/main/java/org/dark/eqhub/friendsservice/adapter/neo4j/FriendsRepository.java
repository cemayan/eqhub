package org.dark.eqhub.friendsservice.adapter.neo4j;


import org.dark.eqhub.friendsservice.domain.model.User;
import org.springframework.data.neo4j.repository.ReactiveNeo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface FriendsRepository extends ReactiveNeo4jRepository<User, Long> {
    Mono<User> findByUserName(String userName);

    @Query("MATCH (user:User) <-[p:FOLLOWERS]- (target:User) "
            + "WHERE user.userName= $userName "
            + "RETURN target")
    Flux<User> findByFollowers(String userName);

}
