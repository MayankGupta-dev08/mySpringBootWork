package dev.mayank.infinityschoolhouse.security;

import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.model.Role;
import dev.mayank.infinityschoolhouse.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@SuppressWarnings("unused")
public class ISHUserAuthenticationProvider implements AuthenticationProvider {
    private static final String ROLE_PREFIX = "ROLE_";

    private PersonRepository personRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ISHUserAuthenticationProvider(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emailAddress = authentication.getName();
        String password = authentication.getCredentials().toString();
        Person person = personRepository.readByEmail(emailAddress);
        if (null != person && person.getPersonId() > 0 && passwordEncoder.matches(password, person.getPwd())) {
            return new UsernamePasswordAuthenticationToken(person.getName(), null,
                    getGrantedAuthorities(person.getRole()));
        }
        throw new BadCredentialsException("Invalid credentials!");
    }

    private Collection<? extends GrantedAuthority> getGrantedAuthorities(Role role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName()));
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
