package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.*;

import java.sql.*;

public class DataBase {
    static Connection con;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/hobbies",
                "zhatab", "zhatab");
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
