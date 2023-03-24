package org.dark.eqhub.postservice.writeapi.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    @Bean
    ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
