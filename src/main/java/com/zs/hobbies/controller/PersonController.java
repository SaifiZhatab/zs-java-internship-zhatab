package com.zs.hobbies.controller;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.service.PersonService;
import com.zs.hobbies.service.PersonServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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

    public PersonController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        personService = new PersonServiceImpl(con, lru);

        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This funciton help you to insert the person object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert(Person person) throws SQLException, InvalidInputException {
        personService.insert(person);
    }
}
