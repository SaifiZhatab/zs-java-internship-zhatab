package main.java.com.zs.hobbies.service;

import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.database.PersonDataBase;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonServiceImpl implements PersonService {
    PersonDataBase personDataBase;

    public PersonServiceImpl() throws SQLException, ClassNotFoundException {
        personDataBase =  new PersonDataBase();
    }

    @Override
    public void insertPerson(Person person) throws SQLException {
        int check = personDataBase.insertPerson(person);

        if(check == 1) {
            System.out.println("Successfully person enter in database");
        }else {
            System.out.println("Some internally error comes.Please try again");
        }
    }

}
