package com.zs.hobbies.util;

import com.zs.hobbies.Application;
import com.zs.hobbies.exception.InvalidInputException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class is help you to connect with database
 */
public class DataBase {
    private Connection con;
    private static Logger logger;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DataBase() throws ClassNotFoundException, SQLException, IOException {

        Class.forName("org.postgresql.Driver");
        try {
            con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobbies", "zhatab", "zhatab");
        }catch (Exception ex) {
            throw new InvalidInputException(500 , "Connection not create");
        }
        logger = Logger.getLogger(Application.class.getName());

        logger.info("DataBase connect successfully ");
    }

    /**
     * This function return the connection
     * @return  connection
     */
    public Connection getCon() {
        return con;
    }

    /**
     * This function set the connection
     * @param con the connection object
     */
    public void setCon(Connection con) {
        this.con = con;
    }
}
