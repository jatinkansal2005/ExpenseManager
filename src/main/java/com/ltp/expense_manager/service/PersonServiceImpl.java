
package com.ltp.expense_manager.service;
import com.ltp.expense_manager.repository.PersonRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.ltp.expense_manager.entity.Person;


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
        return personRepository.findById(id).get();
    }
    //Have to handle the case of no person in future. 
    public Person updatePerson(Long id, Person newPerson){
        Person existingPerson = personRepository.findById(id).get();
        existingPerson.setAge(newPerson.getAge());
        existingPerson.setEmailId(newPerson.getEmailId());
        existingPerson.setName(newPerson.getName());
        existingPerson.setPassword(newPerson.getPassword());
        return personRepository.save(existingPerson);
    }
}   
