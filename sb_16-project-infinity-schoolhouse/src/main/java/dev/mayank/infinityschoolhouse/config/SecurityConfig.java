package dev.mayank.infinityschoolhouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@SuppressWarnings("unused")
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, HandlerMappingIntrospector introspector) throws Exception {
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/saveMsg"));
        httpSecurity.authorizeRequests(requests -> {
            requests
                    .requestMatchers(new MvcRequestMatcher(introspector, "/about")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/assets/)**")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/contact")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/courses")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/dashboard")).authenticated()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/holidays/)**")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/home")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/login")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/logout")).permitAll()
                    .requestMatchers(new MvcRequestMatcher(introspector, "/saveMsg")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll();
        });
        httpSecurity.formLogin(formLogin -> {
            formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/dashboard")
                    .failureUrl("/login?error=true")
                    .permitAll();
        });
        httpSecurity.logout(logout -> {
            logout
                    .logoutSuccessUrl("/login?logout=true")
                    .invalidateHttpSession(true)
                    .permitAll();
        });
        httpSecurity.httpBasic(withDefaults());
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