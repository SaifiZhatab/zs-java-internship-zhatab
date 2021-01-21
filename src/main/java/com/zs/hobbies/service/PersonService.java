package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.dto.Person;

import java.sql.SQLException;

/**
 * This is a remote class of Person.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface PersonService {
    void insertPerson(Person person) throws SQLException;
}
