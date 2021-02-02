package com.zs.hobbies.service;

import com.zs.hobbies.Application;
import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.dao.PersonDao;
import com.zs.hobbies.dto.Person;
import com.zs.hobbies.util.DataBase;
import com.zs.hobbies.util.ResourceLoader;
import com.zs.hobbies.validator.Validator;

import java.util.logging.Logger;

/**
 * This class give service to person
 */
public class PersonServiceImpl implements PersonService {
    private PersonDao personDao;
    private Cache lru;
    private Logger logger;
    private Validator validator;

    /**
     * This is constructor and it set the connection and lru object
     */
    public PersonServiceImpl() {
        logger = Logger.getLogger(Application.class.getName());
        validator = new Validator();
        this.lru = new Cache(ResourceLoader.getCacheSize());
        personDao =  new PersonDao(DataBase.getCon());
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
        validator.validPerson(person);
        personDao.insert(person);
    }

}
