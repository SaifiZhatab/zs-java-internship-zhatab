package main.java.com.zs.hobbies.database;

import main.java.com.zs.hobbies.entity.Chess;
import main.java.com.zs.hobbies.entity.Person;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChessDataBase {
    private PreparedStatement insertChess, dateChessDetails, lastTick,longestChessStreak;

    /**
     * This is constructor which help you to connect your program to database
     * set all the prepare statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public ChessDataBase() throws ClassNotFoundException, SQLException {
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

    public ResultSet dateChessDetails(Person person, Date date) throws SQLException {
        dateChessDetails.setInt(1,person.getId());
        dateChessDetails.setDate(2,date);

        return dateChessDetails.executeQuery();
    }
    public ResultSet lastTick(Person person) throws SQLException {
        lastTick.setInt(1,person.getId());
        return lastTick.executeQuery();
    }

    public ResultSet longestChessStreak(Person person) throws SQLException {
        longestChessStreak.setInt(1,person.getId());
        return longestChessStreak.executeQuery();
    }
}
