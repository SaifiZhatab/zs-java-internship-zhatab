package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PersonDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertPerson, findHigherKey;

    public PersonDao(Connection con) throws SQLException, IOException, ClassNotFoundException {
       logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Person database start ");


        this.con = con;
        insertPerson = con.prepareStatement("insert into Persons values (?,?,?,?)");
        findHigherKey = con.prepareStatement("select personid from Persons order by personid desc LIMIT 1");
    }

    /**
     * this function help you to insert the user entity in database
     * @param person    the person object
     * @return      return the status of insert query
     * @throws SQLException
     */
    public int insertPerson(Person person) throws SQLException {
        insertPerson.setInt(1,person.getId());
        insertPerson.setString(2, person.getName());
        insertPerson.setString(3, person.getMobile());
        insertPerson.setString(4, person.getAddress());

        return insertPerson.executeUpdate();
    }

    /**
     * This class help you to find the unique key that will not present in database table
     * @return      return the unique key of table
     * @throws SQLException
     */
    public int findHigherKey() throws SQLException {
        ResultSet resultSet = findHigherKey.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt(1) + 1;
        }else {
            return 1;
        }
    }
}
