package com.zs.hobbies.service;

import com.zs.hobbies.dto.Badminton;

/**
 * This is a remote class of Badminton.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface BadmintonService extends HobbyService {
    /**
     * This function help you to insert the badminton object in database
     * @param badminton the badminton object
     */
    void insert(Badminton badminton);
}
