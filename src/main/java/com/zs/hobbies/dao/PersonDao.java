package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PersonDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insert;

    public PersonDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        this.con = con;
    }

    /**
     * this function help you to insert the user entity in database
     * @param person    the person object
     * @return      return the status of insert query
     */
    public void insert(Person person) {
        if(person == null) {
            throw new InvalidInputException(500,"Sorry, Null object pass in person database");
        }
        try{
            insert = con.prepareStatement("insert into Persons values (?,?,?,?)");
            insert.setInt(1,person.getId());
            insert.setString(2, person.getName());
            insert.setString(3, person.getMobile());
            insert.setString(4, person.getAddress());

            insert.executeUpdate();
            logger.info("Successfully insert person in database");
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in person database");
        }
    }
}
