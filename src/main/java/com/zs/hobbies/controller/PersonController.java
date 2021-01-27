package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.Application;
import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.PersonService;
import main.java.com.zs.hobbies.service.PersonServiceImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This is a Person Controller class
 * that call the person service call and using service interact with database
 */
public class PersonController {
    private Person person;
    PersonService personService;
    private Logger logger;

    Scanner in = new Scanner(System.in);

    public PersonController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        personService = new PersonServiceImpl(con,lru);

        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());
    }

    /**
     * This function check the name is valid or not
     * it return false if any value come except charcharcter otherwise it return true
     * @param check the name string
     * @return true/false
     */
    boolean checkName(String check) {
        int length = check.length();

        /**
         * check only Character present in name
         */
        for(int i=0;i<length;i++) {
            if((check.charAt(i)>= 'a' && check.charAt(i) <= 'z') ||
                    (check.charAt(i)>='A' && check.charAt(i) <= 'Z') ) {

            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * This function check the mobile number is valid or not
     * it return true if the mobile number is valid
     * @param mobile  the mobile number string
     * @return true/false
     */
    boolean checkMobile(String mobile) {
        int length = mobile.length();

        /**
         * check all digit in mobile number is digit
         */
        for(int i=0;i<length;i++) {
           if(mobile.charAt(i) >= '0' && mobile.charAt(i) <='9'){}
           else {
               return false;
           }
       }

        /**
         * if the length of mobile number doesn't equal to 10
         */
        if(length != 10 ) {
           return false;
       }else {
           return true;
       }
    }

    /**
     * This funciton help you to insert the person object in database
     * @throws ParseException
     * @throws SQLException
     */
    public void insert() throws SQLException, InvalidInputException {

        String name, mobile, address;

            logger.info("Enter person name = ");
            name = in.next();

            if(!checkName(name)) {
                throw new InvalidInputException(400,"Input mismatch exception ");
            }

            logger.info("Enter person mobile number = ");
            mobile = in.next();

            if(!checkMobile(mobile)) {
                throw new InvalidInputException(400,"Input mismatch exception");
            }

            logger.info("Enter person address = ");
            address = in.next();

            person = new Person(name,mobile,address);
            personService.insert(person);
    }
}
