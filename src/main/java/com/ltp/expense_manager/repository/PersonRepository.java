package com.ltp.expense_manager.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import com.ltp.expense_manager.entity.Person;
public interface PersonRepository extends CrudRepository<Person,Long>{
    Optional<Person> findByEmailId(String emailId);
}
