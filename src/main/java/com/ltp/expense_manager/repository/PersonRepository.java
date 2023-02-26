package com.ltp.expense_manager.repository;

import org.springframework.data.repository.CrudRepository;


import com.ltp.expense_manager.entity.Person;
public interface PersonRepository extends CrudRepository<Person,Long>{
    
}
