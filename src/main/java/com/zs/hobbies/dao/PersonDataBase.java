package main.java.com.zs.hobbies.dao;

import main.java.com.zs.hobbies.Controller;
import main.java.com.zs.hobbies.dto.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class PersonDataBase {
    private Logger logger;
    private PreparedStatement insertPerson;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public PersonDataBase() throws ClassNotFoundException, SQLException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("Successfully Person database start ");

        insertPerson = DataBase.con.prepareStatement("insert into Persons values (?,?,?,?)");
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
}
