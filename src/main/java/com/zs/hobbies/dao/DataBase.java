package main.java.com.zs.hobbies.dao;

import main.java.com.zs.hobbies.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class is help you to connect with database
 */
public class DataBase {
    static Connection con;
    private static Logger logger;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DataBase() throws ClassNotFoundException, SQLException, IOException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobbies", "zhatab", "zhatab");

        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("DataBase connect successfully ");
    }

    /**
     * This class help you to find the unique key that will not present in database table
     * @param table_name    database table name
     * @param column_name   column name primary key of table
     * @return      return the unique key of table
     * @throws SQLException
     */
    public static int findHigherKey(String table_name,String column_name) throws SQLException {
        Statement findHigherKey = con.createStatement();
        ResultSet rs = findHigherKey.executeQuery(" select * from "+ table_name+" order by " +column_name +" desc limit 1");

        if(rs.next()) {
            return rs.getInt(1) +1;
        }else {
            return 1;
        }
    }

}
