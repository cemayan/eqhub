package org.dark.eqhub.authserver;


import org.dark.eqhub.authserver.adapter.UserRepository;
import org.dark.eqhub.authserver.domain.model.EqhubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EqhubUser eqhubUser = userRepository.findByUserName(username);
        if (eqhubUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return User.withUsername(eqhubUser.getEmail()).password(eqhubUser.getPassword()).authorities("USER").build();
    }
}