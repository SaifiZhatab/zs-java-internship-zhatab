package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.PersonService;
import com.zs.hobbies.service.PersonServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

/**
 * This is a Person Controller class
 * that call the person service call and using service interact with database
 */
@RestController
public class PersonController {
    private Logger logger;
    private PersonService personService;

    public PersonController() {
        logger = Logger.getLogger(Application.class.getName());
        personService = new PersonServiceImpl();
    }

    /**
     * This function help you to insert the person object in database
     */
    @PostMapping("Person/insert")
    public ResponseEntity insert(@RequestBody Person person) {
            personService.insert(person);
            return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
}
