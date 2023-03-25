package org.dark.eqhub.authservice.domain.service;


import org.dark.eqhub.authservice.adapter.UserRepository;
import org.dark.eqhub.authservice.domain.model.EqhubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class CustomUserDetailService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<EqhubUser> eqhubUser = userRepository.findByUserName(username);
        if (eqhubUser.isEmpty()) {
            logger.error("User not found");
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(eqhubUser.get().getEmail()).password(eqhubUser.get().getPassword()).authorities("USER").build();
    }
}