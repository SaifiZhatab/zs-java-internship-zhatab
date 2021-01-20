package main.java.com.zs.hobbies;

import main.java.com.zs.hobbies.database.DataBase;

import java.sql.SQLException;

public class Main {

    public static void main(String st[]) throws SQLException, ClassNotFoundException {
        DataBase db = new DataBase();
        db.query();
    }
}
