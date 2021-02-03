package com.zs.hobbies.service;

import com.zs.hobbies.dto.Person;


/**
 * This is a remote class of Person.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface PersonService {
    void insert(Person person) ;
}
