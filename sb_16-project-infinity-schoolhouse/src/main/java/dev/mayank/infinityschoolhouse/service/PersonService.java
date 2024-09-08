package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.model.Role;
import dev.mayank.infinityschoolhouse.repository.PersonRepository;
import dev.mayank.infinityschoolhouse.repository.RoleRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {
    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createNewPerson(Person person) {
        try {
            Role role = roleRepository.getByRoleName(ISHConstants.STUDENT_ROLE);
            person.setRole(role);
            person.setPwd(passwordEncoder.encode(person.getPwd()));
            Person saved = personRepository.save(person);
            if (null != saved && saved.getPersonId() > 0) return true;
        } catch (Exception e) {
            log.error("Error occurred while saving the person: {}", e.getMessage());
        }
        log.error("Person could not be saved. Please check the logs for more details.");
        return false;
    }
}