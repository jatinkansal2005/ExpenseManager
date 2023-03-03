package com.ltp.expense_manager.service;
import com.ltp.expense_manager.entity.Person;
public interface PersonService {
    public Person savePerson(Person person);
    public void deletePerson(Long id);
    public Person getPerson(Long id);
    public Person updatePerson(Long id, Person newPerson);
}
