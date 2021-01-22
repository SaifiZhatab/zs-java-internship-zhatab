package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.dao.PersonDataBase;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.extrafeature.Lru;
import main.java.com.zs.hobbies.extrafeature.Node;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class give service to person
 */
public class PersonServiceImpl implements PersonService {
    private PersonDataBase personDataBase;
    private Lru lru;
    private Logger logger;

    public PersonServiceImpl(Connection con,Lru lru) throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Person Service start ");

        this.lru = lru;
        personDataBase =  new PersonDataBase(con);
    }

    /**
     * This function help you to insert the person in database
     * @param person    the person object
     * @throws SQLException
     */
    @Override
    public void insert(Person person) throws SQLException {
        /**
         * if user doesn't give id, then it take automatically
         */
        if(person.getId() == -1) {
            person.setId(personDataBase.findHigherKey());
        }
        lru.refer(new Node(person));

        int check = personDataBase.insertPerson(person);

        if(check == 1) {
            logger.info("Successfully person enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

}
