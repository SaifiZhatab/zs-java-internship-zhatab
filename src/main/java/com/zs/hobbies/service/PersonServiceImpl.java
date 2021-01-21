package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Controller;
import main.java.com.zs.hobbies.dao.PersonDataBase;
import main.java.com.zs.hobbies.dto.Person;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class give service to person
 */
public class PersonServiceImpl implements PersonService {
    PersonDataBase personDataBase;
    private Logger logger;

    public PersonServiceImpl() throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("Successfully Person Service start ");

        personDataBase =  new PersonDataBase();
    }

    /**
     * This function help you to insert the person in database
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void insertPerson(Person person) throws SQLException {
        int check = personDataBase.insertPerson(person);

        if(check == 1) {
            logger.info("Successfully person enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

}
