package com.felix.market.web.security;

import com.felix.market.domain.service.FelixUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FelixUserDetailsService felixUserDetailsService;

    @Bean
    public AuthenticationManager authManager(HttpSecurity security) throws Exception {
        return security.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(felixUserDetailsService)
                .and().build();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

        return security.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
                .anyRequest()
                .authenticated()
                .and().build();
    }
}
