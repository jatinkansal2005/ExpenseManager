package com.ltp.expense_manager.repository;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

import com.ltp.expense_manager.entity.Person;
public interface PersonRepository extends CrudRepository<Person,Long>{
    Optional<Person> findByEmailId(String emailId);
}
