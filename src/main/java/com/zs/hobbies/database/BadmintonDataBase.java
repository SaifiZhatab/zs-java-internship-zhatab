package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.Badminton;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BadmintonDataBase {
    private PreparedStatement insertBadminton, dateBadmintonDetails, longestBadmintonStreak,
                            lastTick;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public BadmintonDataBase() throws ClassNotFoundException, SQLException {
        insertBadminton = DataBase.con.prepareStatement("insert into Badminton values (?,?,?,?,?,?,?)");
        longestBadmintonStreak = DataBase.con.prepareStatement("select * from Badminton where personid = ? order by day");
        dateBadmintonDetails = DataBase.con.prepareStatement("select * from Badminton where personid = ? and day=?");
        lastTick = DataBase.con.prepareStatement("select * from Badminton where personid = ? order by badminton_id desc LIMIT 1");
    }

    /**
     * this function help you to insert the Badminton hobbies in database
     * @param badminton this is a badminton object
     * @return   return status
     * @throws SQLException
     */
    public int insertBadminton(Badminton badminton) throws SQLException {
        insertBadminton.setInt(1,badminton.getId());
        insertBadminton.setInt(2,badminton.getPerson().getId());
        insertBadminton.setTime(3,badminton.getTime().getStartTime());
        insertBadminton.setTime(4,badminton.getTime().getEndTime());
        insertBadminton.setInt(5,badminton.getNumPlayers());
        insertBadminton.setString(6,badminton.getResult());
        insertBadminton.setDate(7,badminton.getTime().getDay());

        return insertBadminton.executeUpdate();
    }

    /**
     * this function send all the person badminton details
     * @param person
     * @return
     * @throws SQLException
     */
    public ResultSet longestBadmintonStreak(Person person) throws SQLException {
        longestBadmintonStreak.setInt(1,person.getId());
        return longestBadmintonStreak.executeQuery();
    }

    public ResultSet dateBadmintonDetails(Person person, Date date) throws SQLException {
        dateBadmintonDetails.setInt(1,person.getId());
        dateBadmintonDetails.setDate(2,date);
        return dateBadmintonDetails.executeQuery();
    }

    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }
}
