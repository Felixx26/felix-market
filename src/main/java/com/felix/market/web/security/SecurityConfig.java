package com.felix.market.web.security;

import com.felix.market.domain.service.FelixUserDetailsService;
import com.felix.market.web.security.filter.JwtFilterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private FelixUserDetailsService felixUserDetailsService;

    @Autowired
    private JwtFilterRequest filterRequest;

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
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(filterRequest, UsernamePasswordAuthenticationFilter.class).build();
    }
}
