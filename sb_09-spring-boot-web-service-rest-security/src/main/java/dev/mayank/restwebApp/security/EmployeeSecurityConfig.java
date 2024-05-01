package dev.mayank.restwebApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@SuppressWarnings("unused")
public class EmployeeSecurityConfig {
    private static final String USERS_TABLE_NAME = "our_members";   // users
    private static final String USERNAME_FIELD_IN_USERS_TABLE = "user_name";    // username
    private static final String PASSWORD_FIELD_IN_USERS_TABLE = "pwd";  // password
    private static final String ENABLED_FIELD_IN_USERS_TABLE = "active";    // enabled
    private static final String AUTHORITIES_TABLE = "our_roles";    // authorities
    private static final String USERNAME_FIELD_IN_AUTHORITIES_TABLE = USERNAME_FIELD_IN_USERS_TABLE;  // username
    private static final String AUTHORITY_FIELD_IN_AUTHORITIES_TABLE = "role";  // authority

    /**
     * Provides support for JDBC, allowing storing users and roles in the database.
     * Custom queries can be used if the table and field names differ from Spring Security defaults.
     *
     * @param dataSource The DataSource used for accessing the database.
     * @return A UserDetailsManager instance configured to use JDBC for user and role management.
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        handleQueryInCustomTablesForSpringSecurity(jdbcUserDetailsManager);
        return jdbcUserDetailsManager;
    }

    /**
     * Configures security for HTTP requests, specifying access control rules based on request methods and URL patterns.
     * Enables HTTP Basic authentication and disables CSRF protection.
     *
     * @param httpSecurity The HttpSecurity object used to configure security for HTTP requests.
     * @return A SecurityFilterChain instance configured with access control rules, HTTP Basic authentication,
     * and CSRF protection disabled.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

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

        // In General: Not meant for REST APIs for non-browser clients. It's needed for web apps with HTML forms which add/update the data.
        httpSecurity.csrf(AbstractHttpConfigurer::disable); // Disable CSRF (Cross Site Request Forgery)

        return httpSecurity.build();
    }

    /**
     * Configures custom queries for retrieving user and authority details from custom tables.
     *
     * @param jdbcUserDetailsManager The JdbcUserDetailsManager instance to configure with custom queries.
     */
    private static void handleQueryInCustomTablesForSpringSecurity(JdbcUserDetailsManager jdbcUserDetailsManager) {
        String usernameQueryString = "SELECT %s, %s, %s FROM %s where %s=?"
                .formatted(USERNAME_FIELD_IN_USERS_TABLE, PASSWORD_FIELD_IN_USERS_TABLE, ENABLED_FIELD_IN_USERS_TABLE, USERS_TABLE_NAME, USERNAME_FIELD_IN_USERS_TABLE);
        jdbcUserDetailsManager.setUsersByUsernameQuery(usernameQueryString);

        String authoritiesQueryString = "SELECT %s, %s FROM %s where %s=?".
                formatted(USERNAME_FIELD_IN_AUTHORITIES_TABLE, AUTHORITY_FIELD_IN_AUTHORITIES_TABLE, AUTHORITIES_TABLE, USERNAME_FIELD_IN_AUTHORITIES_TABLE);
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(authoritiesQueryString);
    }

    /**
     * Provides in-memory storage for user data and roles for security.
     * This is an alternative to JDBC-based user and role management.
     *
     * @return An InMemoryUserDetailsManager instance configured with predefined user data and roles.
     */
    // @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("rakesh")
                        .password("{noop}sde01")
                        .roles("DEVELOPER").build(),
                User.builder()
                        .username("suresh")
                        .password("{noop}user01")
                        .roles("CLIENT").build(),
                User.builder()
                        .username("mahesh")
                        .password("{noop}admin")
                        .roles("ADMIN").build(),
                User.builder()
                        .username("mayank")
                        .password("{noop}leo")
                        .roles("DEVELOPER", "MANAGER", "ADMIN").build()
        );
    }
}