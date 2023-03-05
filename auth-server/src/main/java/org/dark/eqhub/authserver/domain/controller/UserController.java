package org.dark.eqhub.authserver.domain.controller;

import org.dark.eqhub.authserver.adapter.UserRepository;
import org.dark.eqhub.authserver.domain.model.EqhubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/")
    public void Add(){
        EqhubUser aa = new EqhubUser();
        aa.setEmail("test");
        aa.setUserName("test");
        aa.setPassword(passwordEncoder.encode("test"));

        userRepository.save(aa);

    }
}
