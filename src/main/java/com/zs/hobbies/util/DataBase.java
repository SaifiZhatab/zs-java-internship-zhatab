package main.java.com.zs.hobbies.util;

import main.java.com.zs.hobbies.Application;

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
        con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobbies", "zhatab", "zhatab");

        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Application.class.getName());

        logger.info("DataBase connect successfully ");
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
