package com.zs.hobbies.service;

import com.zs.hobbies.dto.Travelling;

import java.sql.SQLException;

/**
 * This is a remote class of Travelling.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface TravellingService extends HobbyService{
    void insert(Travelling travelling) throws SQLException;
}
