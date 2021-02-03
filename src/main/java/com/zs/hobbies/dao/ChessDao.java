package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Chess;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class ChessDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insert, dateDetails, lastTick,longestStreak;

    public ChessDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        this.con = con;
    }

    /**
     * this function help you to insert the Chess hobbies in database
     * @param chess this is a chess object
     * @return return status
     */
    public void insert(Chess chess) {
        if(chess == null) {
            throw new InvalidInputException(400,"Sorry, Null object pass in Chess database");
        }

       try{
            insert = con.prepareStatement("insert into Chess values (?,?,?,?,?,?,?)");
            insert.setInt(1,chess.getId());
            insert.setInt(2,chess.getPersonId());
            insert.setTime(3,chess.getTime().getStartTime());
            insert.setTime(4,chess.getTime().getEndTime());
            insert.setInt(5,chess.getNumMoves());
            insert.setString(6,chess.getResult());
            insert.setDate(7,chess.getTime().getDay());
            insert.executeUpdate();
            logger.info("Successfully insert chess in database");
       }catch (SQLException e) {
           throw new InvalidInputException(500,"Sorry,you use duplicate key");
       }
    }

    /**
     * this class help you to find the chess details of person at particular date
     * @param personId    the person object
     * @param date      date
     * @return      return the details fetch by database
     */
    public ResultSet dateDetails(int personId, Date date) {
        try{
            dateDetails = con.prepareStatement("select * from Chess where personid = ? and day = ?");
            dateDetails.setInt(1,personId);
            dateDetails.setDate(2,date);
            return dateDetails.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in chess database");
        }
    }


    /**
     * This function help you to find the last tick of chess
     * @param personId    the person object
     * @return      return the last tick by person
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Chess where personid = ? order by chess_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in chess database");
        }
    }

    /**
     * This function help you to find the longest streak of person
     * @param personId    the person object
     * @return      return the all details of person available in database
     */
    public ResultSet longestStreak(int personId) {
        try{
            longestStreak = con.prepareStatement("select * from Chess where personid = ? order by day");
            longestStreak.setInt(1,personId);
            return longestStreak.executeQuery();
        }catch (SQLException e) {
            throw new ApplicationException(500,"Sorry,some internal exception comes in chess database");
        }
    }
}
