package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.PersonDao;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.validator.Validator;

import java.sql.Connection;
import java.util.logging.Logger;

/**
 * This class give service to person
 */
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;
    private Cache lru;
    private Logger logger;
    private Validator validator;

    public PersonServiceImpl(Connection con,Cache lru) {
        logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Person Service start ");
        this.lru = lru;
        validator = new Validator();
        personDao =  new PersonDao(con);
    }

    /**
     * This function help you to insert the person in database
     * @param person    the person object
     */
    @Override
    public void insert(Person person) {
        /**
         * check validity
         */
        validator.checkName(person.getName());
        validator.checkMobile(person.getMobile());

        personDao.insert(person);
    }

}
