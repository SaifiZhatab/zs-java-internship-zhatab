package com.zs.hobbies;

import com.zs.hobbies.cache.Cache;
import com.zs.hobbies.controller.*;
import com.zs.hobbies.dto.*;
import com.zs.hobbies.exception.ApplicationException;
import com.zs.hobbies.exception.InvalidInputException;
import com.zs.hobbies.util.DataBase;
import com.zs.hobbies.util.ResourceLoader;

import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.Scanner;
import java.util.logging.Logger;

public class Application {
    private static Logger logger;
    static Scanner in = new Scanner(System.in);

    /**
     * This function is used to perform all person operation
     * @param personController  person controller class
     * @throws ApplicationException  custom internal server exception
     * @throws InvalidInputException  custom exception exception
     */
    static void personOperation(PersonController personController) throws ApplicationException,InvalidInputException  {
        logger.info("1. for person entry ");
        logger.info("2. exit");

        int choice = 0;
        do{
            logger.info("Enter your choice = ");
            choice = in.nextInt();

            switch (choice) {
                case 1:
                        int id ;
                        String name, mobile, address;

                        logger.info("Enter person id = ");
                        id = in.nextInt();

                        logger.info("Enter person name = ");
                        name = in.next();

                        logger.info("Enter person mobile number = ");
                        mobile = in.next();

                        logger.info("Enter person address = ");
                        address = in.next();

                        Person person = new Person(id,name,mobile,address);

                        personController.insert(person);
                         break;
                default:
                    break;
            }
        }while (choice != 2);
    }

    /**
     * The function is used to perform all badminton operation
     * @param badmintonController  the badminton controller class
     * @throws ApplicationException  custom internal server exception
     * @throws InvalidInputException  custom exception exception
     */
    static void badmintonOperation(BadmintonController badmintonController) throws ApplicationException,InvalidInputException {
        logger.info("1. for insert badminton hobby");
        logger.info("2. for longest streak in badminton");
        logger.info("3. for latest streak in badminton");
        logger.info("4. for last badminton tick");
        logger.info("5. for search by date in badminton");
        logger.info("6. for exit in badminton");

        int choice;
        do{
            logger.info("Enter your choice = ");
            choice = in.nextInt();
            int badminton_id, personId;
            Time startTime, endTime;
            Date date;
            int numOfPlayer;
            String result;

            switch (choice) {
                case 1:
                    logger.info("Enter badminton id = ");
                    badminton_id = in.nextInt();

                    logger.info("Enter user id = ");
                    personId = in.nextInt();

                    logger.info("Enter starting time = ");
                    startTime = Time.valueOf(in.next());

                    logger.info("Enter end time = ");
                    endTime = Time.valueOf(in.next());

                    logger.info("Enter the date(YYYY-MM-DD) = ");
                    date = Date.valueOf(in.next());

                    logger.info("Enter the number of player = ");
                    numOfPlayer = in.nextInt();

                    logger.info("Enter the result = ");
                    result = in.next();

                    Timing time = new Timing(startTime,endTime,date);
                    Badminton badminton = new Badminton(badminton_id,personId,time,numOfPlayer,result);
                    badmintonController.insert(badminton);
                    break;

                case 2:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    badmintonController.longestStreak(personId);
                    break;

                case 3:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    badmintonController.latestStreak(personId);
                    break;

                case 4:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    badmintonController.lastTick(personId);
                    break;

                case 5:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    logger.info("enter date = ");
                    date = Date.valueOf(in.next());
                    badmintonController.searchByDate(personId,date);
                    break;

                default:
                    break;
            }
        }while(choice != 6);
    }

    /**
     * The function is used to perform all Chess operation
     * @param chessController  the chess controller class
     * @throws ApplicationException  custom internal server exception
     * @throws InvalidInputException  custom exception exception
     */
    static void chessOperation(ChessController chessController) throws ApplicationException,InvalidInputException  {
        logger.info("1. for insert chess hobby");
        logger.info("2. for longest streak in chess");
        logger.info("3. for latest streak in chess");
        logger.info("4. for last chess tick");
        logger.info("5. for search by date in chess");
        logger.info("6. for exit in chess");

        int choice;
        do{
            logger.info("Enter your choice = ");
            choice = in.nextInt();
            int chessId, personId;
            Time startTime, endTime;
            Date date;
            int numOfMoves;
            String result;

            switch (choice) {
                case 1:
                    logger.info("Enter badminton id = ");
                    chessId = in.nextInt();

                    logger.info("Enter user id = ");
                    personId = in.nextInt();

                    logger.info("Enter starting time = ");
                    startTime = Time.valueOf(in.next());

                    logger.info("Enter end time = ");
                    endTime = Time.valueOf(in.next());

                    logger.info("Enter the date = ");
                    date = Date.valueOf(in.next());

                    logger.info("Enter the number of player = ");
                    numOfMoves = in.nextInt();

                    logger.info("Enter the result = ");
                    result = in.next();

                    Timing time = new Timing(startTime,endTime,date);
                    Chess chess = new Chess(chessId,personId,time,numOfMoves,result);
                    chessController.insert(chess);
                    break;

                case 2:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    chessController.longestStreak(personId);
                    break;

                case 3:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    chessController.latestStreak(personId);
                    break;

                case 4:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    chessController.lastTick(personId);
                    break;

                case 5:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    logger.info("enter date = ");
                    date = Date.valueOf(in.next());
                    chessController.searchByDate(personId,date);
                    break;

                default:
                    break;
            }
        }while(choice < 6);
    }

    /**
     * The function is used to perform all travelling operation
     * @param travellingController  the travelling controller class
     * @throws ApplicationException  custom internal server exception
     * @throws InvalidInputException  custom exception exception
     */
    static void travellingOperation(TravellingController travellingController)throws ApplicationException,InvalidInputException  {
        logger.info("1. for insert travelling hobby");
        logger.info("2. for longest streak in travelling");
        logger.info("3. for latest streak in travelling");
        logger.info("4. for last travelling tick");
        logger.info("5. for search by date in travelling");
        logger.info("6. for exit in travelling");

        int choice;
        do{
            logger.info("Enter your choice = ");
            choice = in.nextInt();
            int travellingId, personId;
            Time startTime, endTime;
            Date date;
            String startPosition, endPosition;
            float distance;

            switch (choice) {
                case 1:
                    logger.info("Enter travelling id = ");
                    travellingId = in.nextInt();

                    logger.info("Enter user id = ");
                    personId = in.nextInt();

                    logger.info("Enter starting time = ");
                    startTime = Time.valueOf(in.next());

                    logger.info("Enter end time = ");
                    endTime = Time.valueOf(in.next());

                    logger.info("Enter the date = ");
                    date = Date.valueOf(in.next());

                    logger.info("Enter the starting position = ");
                    startPosition = in.next();

                    logger.info("Enter the ending position = ");
                    endPosition = in.next();

                    logger.info("Enter total distance = ");
                    distance = in.nextFloat();

                    Timing time = new Timing(startTime,endTime,date);
                    Travelling travelling = new Travelling(travellingId,personId,time,startPosition,endPosition,distance);
                    travellingController.insert(travelling);
                    break;

                case 2:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    travellingController.longestStreak(personId);
                    break;

                case 3:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    travellingController.latestStreak(personId);
                    break;

                case 4:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    travellingController.lastTick(personId);
                    break;

                case 5:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    logger.info("enter date = ");
                    date = Date.valueOf(in.next());
                    travellingController.searchByDate(personId,date);
                    break;

                default:
                    break;
            }
        }while(choice < 6);
    }

    /**
     * The function is used to perform all video watching operation
     * @param videoWatchingController  the video watching controller class
     * @throws ApplicationException  custom internal server exception
     * @throws InvalidInputException  custom exception exception
     */
    static void videoWatchingOperation(VideoWatchingController videoWatchingController) throws ApplicationException,InvalidInputException {
        logger.info("1. for insert video watching hobby");
        logger.info("2. for longest streak in video watching");
        logger.info("3. for latest streak in video watching");
        logger.info("4. for last video watching tick");
        logger.info("5. for search by date in video watching");
        logger.info("6. for exit in video watching");

        int choice;
        do{
            logger.info("Enter your choice = ");
            choice = in.nextInt();
            int videoWatchingId, personId;
            Time startTime, endTime;
            Date date;
            String title;

            switch (choice) {
                case 1:
                    logger.info("Enter badminton id = ");
                    videoWatchingId = in.nextInt();

                    logger.info("Enter user id = ");
                    personId = in.nextInt();

                    logger.info("Enter starting time = ");
                    startTime = Time.valueOf(in.next());

                    logger.info("Enter end time = ");
                    endTime = Time.valueOf(in.next());

                    logger.info("Enter the date = ");
                    date = Date.valueOf(in.next());


                    logger.info("Enter the title video watching = ");
                    title = in.next();

                    Timing time = new Timing(startTime,endTime,date);
                    VideoWatching videoWatching = new VideoWatching(videoWatchingId,personId,time,title);
                    videoWatchingController.insert(videoWatching);
                    break;

                case 2:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    videoWatchingController.longestStreak(personId);
                    break;

                case 3:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    videoWatchingController.latestStreak(personId);
                    break;

                case 4:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();
                    videoWatchingController.lastTick(personId);
                    break;

                case 5:
                    logger.info("Enter person id = ");
                    personId = in.nextInt();

                    logger.info("enter date = ");
                    date = Date.valueOf(in.next());
                    videoWatchingController.searchByDate(personId,date);
                    break;

                default:
                    break;
            }
        }while(choice < 6);
    }

    public static void main(String st[]) {

        try{
            /**
             * set logger class
             */
             ResourceLoader.loggerManager();
             logger = Logger.getLogger(Application.class.getName());

            /**
             * fetch cache memory size in application.properties file
             * and set into the cache memory
             * properties.getProperty("cache") give exception when the value is not present or when the value is not integer
             */
            ResourceLoader.loadApplicationResource();
            int capacity = Integer.parseInt(ResourceLoader.getParameter("cache")) ;

            Cache lru = new Cache(capacity);

            DataBase dataBase = new DataBase();
            PersonController personController = new PersonController(dataBase.getCon(),lru);
            BadmintonController badmintonController = new BadmintonController(dataBase.getCon(),lru);
            ChessController chessController = new ChessController(dataBase.getCon(),lru);
            TravellingController travellingController = new TravellingController(dataBase.getCon(),lru);
            VideoWatchingController videoWatchingController = new VideoWatchingController(dataBase.getCon(),lru);


            int choice;
            do{
                logger.info("1. perform operation on Person");
                logger.info("2. perform operation on Badminton hobby");
                logger.info("3. perform operation on chess hobby");
                logger.info("4. perform operation on travelling hobby");
                logger.info("5. perform operation on video watching hobby");
                logger.info("6. for exit");

                logger.info("Enter your choice = ");
                choice = in.nextInt();

                switch (choice) {
                    case 1:
                        personOperation(personController);
                        break;

                    case 2:
                        badmintonOperation(badmintonController);
                        break;

                    case 3:
                        chessOperation(chessController);
                        break;

                    case 4:
                        travellingOperation(travellingController);
                        break;
                    case 5:
                        videoWatchingOperation(videoWatchingController);
                        break;
                    default:
                        break;
                }

            }while (choice < 6);


            dataBase.getCon().close();

        }catch (InvalidInputException e) {
            logger.warning("Error code : " + e.getErrorCode() + "   Error message : " + e.getErrorDescription());
        }catch(ApplicationException e) {
            logger.warning("Error code : " + e.getErrorCode() + "   Error message : " + e.getErrorDescription());
        }catch (SQLException e) {
            logger.warning("Sorry, some internal error comes in application");
        }
    }
}
