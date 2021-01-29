package com.zs.hobbies.service;

import com.zs.hobbies.dto.Chess;

/**
 * This is a remote class of Chess.
 * this class help you to achieve run time polymorphism.
 * this class help you to implement abstraction
 * this class control all the functionality
 */
public interface ChessService extends HobbyService{
    void insert(Chess chess) ;
}
