package main.java.com.zs.hobbies.dao;

import main.java.com.zs.hobbies.Controller;
import main.java.com.zs.hobbies.dto.Person;
import main.java.com.zs.hobbies.dto.Travelling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class TravellingDataBase {
    private Logger logger;
    private PreparedStatement insertTravelling, dateTravellingDetails, lastTick, longestTravellingStreak;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public TravellingDataBase() throws ClassNotFoundException, SQLException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("Successfully Travelling database start ");

        insertTravelling = DataBase.con.prepareStatement("insert into travelling values (?,?,?,?,?,?,?,?)");
        dateTravellingDetails = DataBase.con.prepareStatement("select * from Travelling where personid = ? and day = ?");
        lastTick = DataBase.con.prepareStatement("select * from Travelling where personid = ? order by travelling_id desc LIMIT 1");
        longestTravellingStreak = DataBase.con.prepareStatement("select * from Travelling where personid = ? order by day");
    }

    /**
     * this function help you to insert the travelling hobbies in database
     * @param travelling    this is a travel object
     * @return  return status of insert query
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

    /**
     * This function help you to fetch the travelling data of person on particular date
     * @param person    the person object
     * @param date      particular date
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet dateTravellingDetails(Person person, Date date) throws SQLException {
        dateTravellingDetails.setInt(1,person.getId());
        dateTravellingDetails.setDate(2,date);

        return dateTravellingDetails.executeQuery();
    }

    /**
     * This function help you to fetch the last tick of person
     * @param person the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    /**
     * This function help you to find the longest Streak in travelling
     * @param person    the person object
     * @return      return the fetch data
     * @throws SQLException
     */
    public ResultSet longestTravellingStreak(Person person) throws SQLException {
        longestTravellingStreak.setInt(1,person.getId());
        return longestTravellingStreak.executeQuery();
    }
}
