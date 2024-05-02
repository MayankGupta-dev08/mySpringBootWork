package dev.mayank.employee.app.security;

import dev.mayank.employee.app.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
     * Defines a BCryptPasswordEncoder bean for encoding passwords.
     * This bean is used to securely encode passwords before storing them in the database.
     *
     * @return A BCryptPasswordEncoder instance.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines an authenticationProvider bean using a custom user service and password encoder.
     * This bean is responsible for authenticating users against the provided user service and password encoder.
     *
     * @param userService The custom user service to use for authentication.
     * @return A DaoAuthenticationProvider instance configured with the custom user service and password encoder.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); // set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
        return auth;
    }

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
     * Defines a SecurityFilterChain bean to configure security for HTTP requests.
     * This bean configures access control based on HTTP request methods and URL patterns.
     * It also enables HTTP Basic authentication and disables Cross-Site Request Forgery (CSRF) protection.
     *
     * @param http The HttpSecurity object used to configure security for HTTP requests.
     * @return A SecurityFilterChain instance configured with access control rules, HTTP Basic authentication,
     * and CSRF protection disabled.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
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
}