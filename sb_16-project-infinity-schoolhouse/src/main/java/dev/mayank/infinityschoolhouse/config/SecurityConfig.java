package dev.mayank.infinityschoolhouse.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@SuppressWarnings("unused")
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/saveMsg")
                        .ignoringRequestMatchers(PathRequest.toH2Console()) // Disable CSRF for H2 console
                )
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers("/about").permitAll()
                            .requestMatchers("/assets/**").permitAll()
                            .requestMatchers("/contact").permitAll()
                            .requestMatchers("/courses").permitAll()
                            .requestMatchers("/dashboard").authenticated()
                            .requestMatchers("/holidays/**").permitAll()
                            .requestMatchers("/", "/home").permitAll()
                            .requestMatchers("/login").permitAll()
                            .requestMatchers("/logout").permitAll()
                            .requestMatchers("/saveMsg").permitAll()
                            .requestMatchers(PathRequest.toH2Console()).permitAll() // Allow access to H2 console
                            .anyRequest().authenticated(); // All other requests must be authenticated
                })
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .httpBasic(withDefaults())
                .headers(headers -> headers.frameOptions(
                        HeadersConfigurer.FrameOptionsConfig::sameOrigin) // Allow H2 console to be rendered in a frame
                );

        return httpSecurity.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("54321")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}