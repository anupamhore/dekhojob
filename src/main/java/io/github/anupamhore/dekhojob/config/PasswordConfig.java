package io.github.anupamhore.dekhojob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {

        //Why delegating?
        /*
         It stores a prefix with each hash, like {bcrypt}$2a$10$..
         If you switch algorithms in 2030, old hashes still verify, and
         users get upgraded gradually
         */
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
