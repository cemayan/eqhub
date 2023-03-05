package org.dark.eqhub.authserver.adapter;

import org.dark.eqhub.authserver.domain.model.EqhubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<EqhubUser, Long> {
    EqhubUser findByUserName(String userName);

}
