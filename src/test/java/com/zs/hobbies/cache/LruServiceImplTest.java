package test.java.com.zs.hobbies.cache;

import com.zs.hobbies.cache.LruService;
import com.zs.hobbies.cache.LruServiceImpl;
import com.zs.hobbies.cache.Node;
import com.zs.hobbies.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a testing class for LruService class
 */
class LruServiceImplTest {
    private  int capacity ;
    private LruServiceImpl lru;
    private Person person ;
    private Timing time ;
    private Travelling travelling ;
    private Chess chess;
    private Badminton badminton ;
    private VideoWatching videoWatching ;


    @BeforeEach
    void setUp() throws SQLException {
        capacity = 3;
        lru = new LruServiceImpl(capacity);

        person = new Person(13,"Rihan","9311851147","Dadri");
        time = new Timing(Time.valueOf("09:09:39"),Time.valueOf("10:49:39"), Date.valueOf("2021-01-25"));

        travelling = new Travelling(16,person,time,"delhi","mumbi",5.6f);
        chess  = new Chess(17,person,time,3,"win");
        badminton = new Badminton(20,person,time, 8,"win");
        videoWatching = new VideoWatching(14,person,time,"Lucifer morningstar");
    }

    /**
     * This test function help you to test the put when key is already present in LRU cache
     */
    @Test
    void putWhenKeyIsAlreadyPresent() {
        lru.put(String.valueOf(chess.getPerson().getId()) + "_chess" , new Node(chess));

        /**
         * again insert same key in LRU
         */
        chess.setId(155);
        lru.put(String.valueOf(chess.getPerson().getId()) + "_chess" , new Node(chess));

        /**
         * check the chess dto id is 155
         */
        assertEquals(155,lru.get(String.valueOf(chess.getPerson().getId()) + "_chess").getChess().getId());

    }

    /**
     *  This test function help you to test the put when Lru cache is full
     */
    @Test
    void putWhenSizeIsFull() {
        /**
         * first try to full the LRU cache after that check the object is insert successfully or not in LRU cache
         */
        lru.put(String.valueOf(badminton.getPerson().getId()) + "_badminton" , new Node(badminton));
        lru.put(String.valueOf(chess.getPerson().getId()) + "_chess" , new Node(chess));
        lru.put(String.valueOf(videoWatching.getPerson().getId()) + "_videoWatching" , new Node(videoWatching));


        /**
         * try to insert in cache when cache is full
         */
        lru.put(String.valueOf(travelling.getPerson().getId()) + "_travelling" , new Node(travelling));

        assertEquals(String.valueOf(travelling.getPerson().getId()) + "_travelling",
                lru.get(String.valueOf(travelling.getPerson().getId()) + "_travelling").getKey());

        /**
         * least recent used badminton object will delete after, then check this object will not available in lru
         */
        assertEquals(null , lru.get(String.valueOf(badminton.getPerson().getId()) + "_badminton"));
    }

    /**
     * This test function help you to test the put when Lru isn't full
     */
    @Test
    void putWhenSizeIsNotFull(){
        lru.put(String.valueOf(travelling.getPerson().getId()) + "_travelling" , new Node(travelling));

        assertEquals(String.valueOf(travelling.getPerson().getId()) + "_travelling",
                lru.get(String.valueOf(travelling.getPerson().getId()) + "_travelling").getKey());

    }

    /**
     * This test function help you to test the get function when Key is present in lru cache
     */
    @Test
    void getWhenKeyPresent() {
        lru.put(String.valueOf(chess.getPerson().getId()) + "_chess" , new Node(chess));
        assertEquals(String.valueOf(chess.getPerson().getId()) + "_chess" , lru.get(String.valueOf(chess.getPerson().getId()) + "_chess").getKey());
    }

    /**
     * This test function help you to test the get function when key is not present in LRU cache
     */
    @Test
    void getWhenKeyNotPresent() {
        assertEquals(null , lru.get(String.valueOf(chess.getPerson().getId()) + "_chess"));
    }

    /**
     * This test function help you to check the object is insert successfully or not
     */
    @Test
    void checkIfElementIsInsertedAtHead() {
        Node node = new Node(chess);
        lru.insert(node);

        assertEquals(node.getKey(), lru.getHead().getNext().getKey());
    }

    /**
     * This function help you to check the object is delete successfully or not
     */
    @Test
    void delete() {
        Node chessObject = new Node(chess);

        /**
         * first insert the object in LRU cache
         */
        lru.put(String.valueOf(chess.getPerson().getId()) + "_chess" , chessObject);

        /**
         * delete the object in LRU cache
         */
        lru.delete(chessObject);

        /**
         * check the object is present or not in LRU cache
         * match head next object and last object in lru cache
         */
        assertEquals(lru.getLast() , lru.getHead().getNext());
    }
}