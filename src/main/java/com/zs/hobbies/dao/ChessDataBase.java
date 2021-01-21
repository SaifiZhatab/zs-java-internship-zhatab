package main.java.com.zs.hobbies.dao;

import main.java.com.zs.hobbies.Controller;
import main.java.com.zs.hobbies.dto.Chess;
import main.java.com.zs.hobbies.dto.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ChessDataBase {
    private Logger logger;
    private PreparedStatement insertChess, dateChessDetails, lastTick,longestChessStreak;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ChessDataBase() throws ClassNotFoundException, SQLException, IOException {
        LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resource/logging.properties"));
        logger = Logger.getLogger(Controller.class.getName());

        logger.info("Successfully Chess database start ");

        insertChess = DataBase.con.prepareStatement("insert into Chess values (?,?,?,?,?,?,?)");
        dateChessDetails = DataBase.con.prepareStatement("select * from Chess where personid = ? and day = ?");
        lastTick = DataBase.con.prepareStatement("select * from Chess where personid = ? order by chess_id desc LIMIT 1");
        longestChessStreak = DataBase.con.prepareStatement("select * from Chess where personid = ? order by day");
    }

    /**
     * this function help you to insert the Chess hobbies in database
     * @param chess this is a chess object
     * @return return status
     * @throws SQLException
     */
    public int insertChess(Chess chess) throws SQLException {
        insertChess.setInt(1,chess.getId());
        insertChess.setInt(2,chess.getPerson().getId());
        insertChess.setTime(3,chess.getTime().getStartTime());
        insertChess.setTime(4,chess.getTime().getEndTime());
        insertChess.setInt(5,chess.getNumMoves());
        insertChess.setString(6,chess.getResult());
        insertChess.setDate(7,chess.getTime().getDay());

        return insertChess.executeUpdate();
    }

    /**
     * this class help you to find the chess details of person at particular date
     * @param person    the person object
     * @param date      date
     * @return      return the details fetch by database
     * @throws SQLException
     */
    public ResultSet dateChessDetails(Person person, Date date) throws SQLException {
        dateChessDetails.setInt(1,person.getId());
        dateChessDetails.setDate(2,date);

        return dateChessDetails.executeQuery();
    }


    /**
     * This function help you to find the last tick of chess
     * @param person    the person object
     * @return      return the last tick by person
     * @throws SQLException
     */
    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    /**
     * This function help you to find the longest streak of person
     * @param person    the person object
     * @return      return the all details of person available in database
     * @throws SQLException
     */
    public ResultSet longestChessStreak(Person person) throws SQLException {
        longestChessStreak.setInt(1,person.getId());
        return longestChessStreak.executeQuery();
    }
}
