package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.Person;
import main.java.com.zs.hobbies.entity.Travelling;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TravellingDataBase {
    private PreparedStatement insertTravelling, dateTravellingDetails, lastTick, longestTravellingStreak;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public TravellingDataBase() throws ClassNotFoundException, SQLException {
        insertTravelling = DataBase.con.prepareStatement("insert into travelling values (?,?,?,?,?,?,?,?)");
        dateTravellingDetails = DataBase.con.prepareStatement("select * from Travelling where personid = ? and day = ?");
        lastTick = DataBase.con.prepareStatement("select * from Travelling where personid = ? order by travelling_id desc LIMIT 1");
        longestTravellingStreak = DataBase.con.prepareStatement("select * from Travelling where personid = ? order by day");
    }

    /**
     * this function help you to insert the travelling hobbies in database
     * @param travelling    this is a travel object
     * @return  return status
     * @throws SQLException
     */
    public int insertTravelling(Travelling travelling) throws SQLException {
        insertTravelling.setInt(1,travelling.getId());
        insertTravelling.setInt(2,travelling.getPerson().getId());
        insertTravelling.setTime(3, travelling.getTime().getStartTime());
        insertTravelling.setTime(4,travelling.getTime().getEndTime());
        insertTravelling.setString(5,travelling.getStartPoint());
        insertTravelling.setString(6,travelling.getEndPoint());
        insertTravelling.setFloat(7,travelling.getDistance());
        insertTravelling.setDate(8,travelling.getTime().getDay());

        return insertTravelling.executeUpdate();
    }

    public ResultSet dateTravellingDetails(Person person, Date date) throws SQLException {
        dateTravellingDetails.setInt(1,person.getId());
        dateTravellingDetails.setDate(2,date);

        return dateTravellingDetails.executeQuery();
    }

    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    public ResultSet longestTravellingStreak(Person person) throws SQLException {
        longestTravellingStreak.setInt(1,person.getId());
        return longestTravellingStreak.executeQuery();
    }
}
