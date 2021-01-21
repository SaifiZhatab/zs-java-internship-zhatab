package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDataBase {
    private PreparedStatement insertPerson;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public PersonDataBase() throws ClassNotFoundException, SQLException {
        insertPerson = DataBase.con.prepareStatement("insert into Persons values (?,?,?,?)");
    }

    /**
     * this function help you to insert the user entity in database
     * @param person    this is person object
     * @return
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
