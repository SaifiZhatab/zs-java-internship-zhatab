package com.zs.hobbies.util;

import com.zs.hobbies.Application;
import com.zs.hobbies.exception.ApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * This class is help you to connect with database
 */
public class DataBase {
    private static Connection con;
    private static Logger logger;

    /**
     *  This is constructor which help you to connect your program to database
     * set all the prepare statement
     */
    static  {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/postgres",
                    "postgres", "root123");

            logger = Logger.getLogger(Application.class.getName());
            logger.info("DataBase connect successfully ");
        }catch (SQLException e) {
            throw new ApplicationException(500,"Some internal exception comes in database connectivity");
        }catch (ClassNotFoundException e) {
            throw new ApplicationException(500, "Sorry, class not found");
        }
    }

    /**
     * This function return the connection
     * @return  connection
     */
    public static Connection getCon() {
        return con;
    }

    public static void closeConnection(){
        try{
            con.close();
        }catch (SQLException e){
            throw new ApplicationException(500,"Sorry, some internal error comes in connection close");
        }
    }
}
