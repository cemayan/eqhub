package org.dark.eqhub.authservice.adapter;


import org.dark.eqhub.authservice.domain.model.EqhubUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<EqhubUser, Long> {
    Optional<EqhubUser> findByUserName(String userName);

}