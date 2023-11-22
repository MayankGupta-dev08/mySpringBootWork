package com.dev.mayang.restwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@SuppressWarnings("unused")
public class EmployeeSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails rakesh = User.builder()
                .username("rakesh")
                .password("{noop}sde01")
                .roles("DEVELOPER").build();

        UserDetails suresh = User.builder()
                .username("suresh")
                .password("{noop}man01")
                .roles("MANAGER").build();

        UserDetails mahesh = User.builder()
                .username("mahesh")
                .password("{noop}admin")
                .roles("ADMIN").build();

        UserDetails mayank = User.builder()
                .username("mayank")
                .password("{noop}leo")
                .roles("DEVELOPER", "MANAGER", "ADMIN").build();

        return new InMemoryUserDetailsManager(rakesh, suresh, mahesh, mayank);
    }
}