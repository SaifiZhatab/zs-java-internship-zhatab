package com.zs.hobbies.dao;

import com.zs.hobbies.Application;
import com.zs.hobbies.dto.Chess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Logger;

public class ChessDao {
    private Logger logger;
    private Connection con;
    private PreparedStatement insertChess, dateChessDetails, lastTick,longestChessStreak;

    public ChessDao(Connection con) {
        logger = Logger.getLogger(Application.class.getName());
        logger.info("Successfully Chess database start ");

        this.con = con;
    }

    /**
     * this function help you to insert the Chess hobbies in database
     * @param chess this is a chess object
     * @return return status
     * @throws SQLException
     */
    public void insertChess(Chess chess) {
       try{
            insertChess = con.prepareStatement("insert into Chess values (?,?,?,?,?,?,?)");
            insertChess.setInt(1,chess.getId());
            insertChess.setInt(2,chess.getPersonId());
            insertChess.setTime(3,chess.getTime().getStartTime());
            insertChess.setTime(4,chess.getTime().getEndTime());
            insertChess.setInt(5,chess.getNumMoves());
            insertChess.setString(6,chess.getResult());
            insertChess.setDate(7,chess.getTime().getDay());
            insertChess.executeUpdate();
            logger.info("Successfully insert chess in database");
       }catch (SQLException e) {
           logger.warning(e.getMessage());
       }
    }

    /**
     * this class help you to find the chess details of person at particular date
     * @param personId    the person object
     * @param date      date
     * @return      return the details fetch by database
     * @throws SQLException
     */
    public ResultSet dateChessDetails(int personId, Date date) {
        try{
            dateChessDetails = con.prepareStatement("select * from Chess where personid = ? and day = ?");
            dateChessDetails.setInt(1,personId);
            dateChessDetails.setDate(2,date);

            return dateChessDetails.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }


    /**
     * This function help you to find the last tick of chess
     * @param personId    the person object
     * @return      return the last tick by person
     * @throws SQLException
     */
    public ResultSet lastTick(int personId) {
        try{
            lastTick = con.prepareStatement("select * from Chess where personid = ? order by chess_id desc LIMIT 1");
            lastTick.setInt(1,personId);
            return lastTick.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }

    /**
     * This function help you to find the longest streak of person
     * @param personId    the person object
     * @return      return the all details of person available in database
     * @throws SQLException
     */
    public ResultSet longestChessStreak(int personId) {
        try{
            longestChessStreak = con.prepareStatement("select * from Chess where personid = ? order by day");
            longestChessStreak.setInt(1,personId);
            return longestChessStreak.executeQuery();
        }catch (SQLException e) {
            logger.warning(e.getMessage());
        }
        return null;
    }
}
