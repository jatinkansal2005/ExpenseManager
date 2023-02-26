package com.ltp.expense_manager.web;

import com.ltp.expense_manager.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ltp.expense_manager.service.PersonService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/person")
public class UserController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<Person>savePerson(@RequestBody @Valid Person person){
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus>deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Person>getPerson(@PathVariable Long id){
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Person>updatePerson(@RequestBody Person person, @PathVariable Long id){
        return new ResponseEntity<>(personService.updatePerson(id, person), HttpStatus.OK);
    }
}
