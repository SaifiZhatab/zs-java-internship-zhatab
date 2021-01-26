package main.java.com.zs.hobbies.controller;

import main.java.com.zs.hobbies.cache.LruService;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.exception.InvalidInputException;
import main.java.com.zs.hobbies.service.PersonService;
import main.java.com.zs.hobbies.service.PersonServiceImpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PersonController {
    private Person person;
    PersonService personService;
    Scanner in = new Scanner(System.in);

    public PersonController(Connection con, LruService lru) throws SQLException, IOException, ClassNotFoundException {
        personService = new PersonServiceImpl(con,lru);
    }

    boolean checkString(String check) {
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
    public void insert() throws SQLException, InvalidInputException {

        String name, mobile, address;

            System.out.print("Enter person name = ");
            name = in.next();

            if(!checkString(name)) {
                throw new InvalidInputException(400,"Input mismatch exception ");
            }

            System.out.print("Enter person mobile number = ");
            mobile = in.next();

            if(!checkMobile(mobile)) {
                throw new InvalidInputException(400,"Input mismatch exception");
            }

            System.out.print("Enter person address = ");
            address = in.next();

            person = new Person(name,mobile,address);
            personService.insert(person);
    }
}
