package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dao.PersonDao;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.cache.Node;

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
    private PersonDao personDao;
    private LruService lru;
    private Logger logger;

    public PersonServiceImpl(Connection con,LruService lru) throws SQLException, ClassNotFoundException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("Successfully Person Service start ");

        this.lru = lru;
        personDao =  new PersonDao(con);
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
            person.setId(personDao.findHigherKey());
        }

        lru.put(new Node(person));

        int check = personDao.insertPerson(person);

        if(check == 1) {
            logger.info("Successfully person enter in database");
        }else {
            logger.warning("Some internally error comes.Please try again");
        }
    }

}