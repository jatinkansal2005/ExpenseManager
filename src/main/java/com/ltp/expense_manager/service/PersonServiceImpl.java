
package com.ltp.expense_manager.service;
import com.ltp.expense_manager.repository.PersonRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ltp.expense_manager.entity.Person;
import com.ltp.expense_manager.exception.PersonNotFoundException;


@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{
    PersonRepository personRepository;

    public Person savePerson(Person person){
        return personRepository.save(person);
    }
    public void deletePerson(Long id){
        personRepository.deleteById(id);
    }
    //Have to handle the case of no person in future. 
    public Person getPerson(Long id){
        return unwrapPerson(personRepository.findById(id), id);
    }
    //Have to handle the case of no person in future. 
    public Person updatePerson(Long id, Person newPerson){
        Optional<Person>existingPersonOptional = personRepository.findById(id);
        if(!existingPersonOptional.isPresent()){
            throw new PersonNotFoundException(id);
        }
        Person existingPerson = existingPersonOptional.get();
        existingPerson.setAge(newPerson.getAge());
        existingPerson.setEmailId(newPerson.getEmailId());
        existingPerson.setName(newPerson.getName());
        existingPerson.setPassword(newPerson.getPassword());
        return personRepository.save(existingPerson);
    }
    public Person unwrapPerson(Optional<Person> person, Long id) {
        if (!person.isPresent())
            throw new PersonNotFoundException(id);
        return person.get();
    }
}   
