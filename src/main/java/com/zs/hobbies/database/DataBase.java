package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.type.User;

import java.sql.*;

public class DataBase {
    static Connection con;
    private static PreparedStatement insertUser;

    public DataBase() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/postgres",
                "postgres", "root123");

        insertUser = con.prepareStatement("insert into users values (?,?,?,?)");
    }

    public static int insertUser(User user) throws SQLException {
        insertUser.setInt(1,user.getId());
        insertUser.setString(2,user.getName());
        insertUser.setString(3,user.getMobile());
        insertUser.setString(4,user.getAddress());

        int check = insertUser.executeUpdate();
        return check;
    }
}
