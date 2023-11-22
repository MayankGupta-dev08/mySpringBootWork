package com.dev.mayang.restwebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@SuppressWarnings("unused")
public class EmployeeSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails suresh = User.builder()
                .username("suresh")
                .password("{noop}user01")
                .roles("CLIENT").build();

        UserDetails rakesh = User.builder()
                .username("rakesh")
                .password("{noop}sde01")
                .roles("DEVELOPER").build();

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

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/our-api/our-employees").hasAnyRole("CLIENT", "DEVELOPER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/our-api/our-employees/**").hasAnyRole("CLIENT", "DEVELOPER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/our-api/our-employees").hasRole("DEVELOPER")
                        .requestMatchers(HttpMethod.PUT, "/our-api/our-employees/**").hasAnyRole("DEVELOPER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/our-api/our-employees/**").hasRole("ADMIN")
        );

        // using Basic HTTP authentication
        httpSecurity.httpBasic(Customizer.withDefaults());

        // disable CSRF (Cross Site Request Forgery)
        // In General: Not req for (stateless) REST APIs that uses PUT, POST, DELETE and/or PATCH
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

}