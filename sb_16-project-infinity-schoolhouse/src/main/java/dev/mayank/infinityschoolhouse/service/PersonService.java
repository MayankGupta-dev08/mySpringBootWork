package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.model.Role;
import dev.mayank.infinityschoolhouse.repository.PersonRepository;
import dev.mayank.infinityschoolhouse.repository.RoleRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonService {
    private PersonRepository personRepository;
    private RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public boolean createNewPerson(Person person) {
        try {
            Role role = roleRepository.getByRoleName(ISHConstants.STUDENT_ROLE);
            person.setRole(role);
            Person saved = personRepository.save(person);
            if (null != saved && saved.getPersonId() > 0) return true;
        } catch (Exception e) {
            log.error("Error occurred while saving the person: {}", e.getMessage());
        }
        log.error("Person could not be saved. Please check the logs for more details.");
        return false;
    }
}