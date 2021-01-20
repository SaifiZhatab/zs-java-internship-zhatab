package main.java.com.zs.hobbies;

import main.java.com.zs.hobbies.database.DataBase;
import main.java.com.zs.hobbies.type.User;

import java.sql.SQLException;

public class UserRunner {
    private User user;

    public UserRunner(User user) throws SQLException, ClassNotFoundException {
        this.user = user;
    }

    public void insertData() throws SQLException {
        int check = DataBase.insertUser(user);

        if(check == 1){
            System.out.println("Successfully intern user");
        }else{
            System.out.println("please enter again.Some error come inside");
        }
    }


}
