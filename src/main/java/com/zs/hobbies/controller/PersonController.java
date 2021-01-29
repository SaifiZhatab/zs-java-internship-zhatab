package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.service.PersonService;
import com.zs.hobbies.service.PersonServiceImpl;

import java.sql.Connection;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * This is a Person Controller class
 * that call the person service call and using service interact with database
 */
public class PersonController {
    private Person person;
    private PersonService personService;
    private Logger logger;

    Scanner in = new Scanner(System.in);

    public PersonController(Connection con, Cache lru) {
        personService = new PersonServiceImpl(con, lru);

        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This function help you to insert the person object in database
     */
    public void insert(Person person) {
        personService.insert(person);
    }
}
