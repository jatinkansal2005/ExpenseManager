package com.ltp.expense_manager.web;

import com.ltp.expense_manager.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3000000)
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping("/addPerson")
    public ResponseEntity<HttpStatus> savePerson(@RequestBody @Valid Person person){
        personService.savePerson(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/deletePerson/{id}")
    public ResponseEntity<HttpStatus>deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getPerson/{id}")
    public ResponseEntity<Person>getPerson(@PathVariable Long id){
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }
    
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<HttpStatus>updatePerson(@RequestBody @Valid Person person, @PathVariable Long id){
        personService.updatePerson(id, person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
