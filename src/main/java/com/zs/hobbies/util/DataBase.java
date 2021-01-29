package com.zs.hobbies.util;

import com.zs.hobbies.Application;
import com.zs.hobbies.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * This class is help you to connect with database
 */
public class DataBase {
    private Connection con;
    private static Logger logger;

    /**
     *  This is constructor which help you to connect your program to database
     * set all the prepare statement
     * @throws ApplicationException  internal server exception custom class
     */
    public DataBase() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobbies", "zhatab", "zhatab");

            logger = Logger.getLogger(Application.class.getName());
            logger.info("DataBase connect successfully ");
        }catch (Exception e) {
            throw new ApplicationException(500,"Some internal exception comes in database connectivity");
        }
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
