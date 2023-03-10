package org.dark.eqhub.postservice.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@EnableWebFluxSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {

    @Autowired
    private ReactiveJwtDecoder jwtDecoder;


    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Bean
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        http
                .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/api1/v1/post/**"))
                .authorizeExchange()
                .pathMatchers("/api1/v1/post/**").hasAuthority("SCOPE_message.read")
                .and()
                .oauth2ResourceServer().jwt().jwtDecoder(jwtDecoder);
        return http.build();
    }

}