
package com.ltp.expense_manager.service;

import com.ltp.expense_manager.repository.PersonRepository;

import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ltp.expense_manager.entity.Person;
import com.ltp.expense_manager.exception.EntityNotFoundException;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {
    PersonRepository personRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Person savePerson(Person person) {

        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        return personRepository.save(person);
    }

    public void deletePerson(Long id) {
        personRepository.deleteById(id);
    }

    // Have to handle the case of no person in future.
    public Person getPerson(Long id) {
        return unwrapPerson(personRepository.findById(id), id);
    }

    // Have to handle the case of no person in future.
    public Person updatePerson(Long personId, Person newPerson) {
        Optional<Person> existingPersonOptional = personRepository.findById(personId);
        if (!existingPersonOptional.isPresent()) {
            throw new EntityNotFoundException(personId, Person.class);
        }
        // Person existingPerson = existingPersonOptional.get();
        // existingPerson.setAge(newPerson.getAge());
        // existingPerson.setEmailId(newPerson.getEmailId());
        // existingPerson.setName(newPerson.getName());
        // existingPerson.setPassword(newPerson.getPassword());
        // return personRepository.save(existingPerson);
        newPerson.setId(personId);
        newPerson.setPassword(bCryptPasswordEncoder.encode(newPerson.getPassword()));
        return personRepository.save(newPerson);
    }

    public static Person unwrapPerson(Optional<Person> person, Long id) {
        if (!person.isPresent())
            throw new EntityNotFoundException(id, Person.class);
        return person.get();
    }

    @Override
    public Person getPerson(String emailId) {
        Optional<Person> person = personRepository.findByEmailId(emailId);
        if (person.isPresent()) {
            return person.get();
        }
        throw new EntityNotFoundException(404L,Person.class);
    }

    @Override
    public boolean isValid(String emailId, Long personId) {
        Person person = getPerson(emailId);
        return person.getId() == personId;
    }
}
