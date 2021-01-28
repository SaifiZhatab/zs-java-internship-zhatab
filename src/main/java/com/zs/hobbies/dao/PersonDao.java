package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PersonDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertPerson;

    public PersonDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Person database start ");
        this.con = con;
    }

    /**
     * this function help you to insert the user entity in database
     * @param person    the person object
     * @return      return the status of insert query
     * @throws SQLException
     */
    public void insertPerson(Person person) {
        try{
            insertPerson = con.prepareStatement("insert into Persons values (?,?,?,?)");
            insertPerson.setInt(1,person.getId());
            insertPerson.setString(2, person.getName());
            insertPerson.setString(3, person.getMobile());
            insertPerson.setString(4, person.getAddress());

            insertPerson.executeUpdate();
            logger.info("Successfully insert person in database");
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
    }
}
