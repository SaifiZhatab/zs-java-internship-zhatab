package main.java.com.zs.e_commerce;

import java.io.FileInputStream;
import java.sql.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class help you to interact with e_commerce database
 */
public class E_commerce {
    static Connection con;
    private static Logger logger;
    static Scanner in = new Scanner(System.in);

    /**
     * This is all prepare statement used in this program
     */
    public static PreparedStatement insertInUser, insertInProduct ,printUsers,printProducts ,
            updateUserData, updateProductData,searchInUer,searchInProduct,deleteUser,deleteProduct,
            book_product, lastXDayOrder,topXOrder;

    /**
     * This function initialize the prepareStatement
     * prepareStatement compile one time and run any time
     */
    public static void initialize() throws SQLException {

        insertInProduct = con.prepareStatement("insert into product values (?,?,?,?)");
        insertInUser = con.prepareStatement("insert into users values (?,?,?,?,?)");
        printUsers = con.prepareStatement("select * from users");
        printProducts = con.prepareStatement("select * from product");
        updateUserData = con.prepareStatement("update users set fisrtname=? , lastname = ?,mobile=?,address=? where id = ?");
        updateProductData = con.prepareStatement("update product set name=?, quantity=?,price=? where id=?");
        searchInUer = con.prepareStatement("select * from users where id = ?");
        searchInProduct = con.prepareStatement("select * from product where id = ?");
        deleteUser = con.prepareStatement("delete from users where id = ?");
        deleteProduct = con.prepareStatement("delete from product where id = ?");
        book_product = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?)");
        lastXDayOrder = con.prepareStatement("select * from order where user_id = ? order by order_date desc limit ?");
        topXOrder = con.prepareStatement("select * from orders where user_id = ? order by item desc limit ?");

        logger.info("Successfully create all queries");
    }


    /**
     * This class help you to insert user data in Users table
     * @param id    id of user
     * @param firstName user first name
     * @param lastName  user last name
     * @param mobile    user mobile number
     * @param address   user address
     * @throws SQLException    exception:
     *                              when you aren't connect to database and try to insert
     *                              when you insert duplicate id
     */
    public static void insertInUsers(int id,String firstName,String lastName,
                                     String mobile,String address) throws SQLException {

        if(searchInUser(id)){
            System.out.println("Key already present in user table ");
            return ;
        }
        insertInUser.setInt(1,id);
        insertInUser.setString(2,firstName);
        insertInUser.setString(3,lastName);
        insertInUser.setString(4,mobile);
        insertInUser.setString(5,address);

        int check = insertInUser.executeUpdate();

        if(check == 0){
            logger.warning("Some internal error comes.Please insert again");
        }else{
            logger.info("Successfully insert data in User table");
        }
    }

    /**
     * This class help you to insert product data in product table
     * @param id  product table
     * @param name product name
     * @param quantity product quantity
     * @param cost product cost
     * @throws SQLException exception:
     *                                  when you aren't connect to database and try to insert
     *                                  when you insert duplicate id
     */
    public static void insertInProduct(int id,String name,int quantity,int cost) throws SQLException {
        insertInProduct.setInt(1,id);
        insertInProduct.setString(2,name);
        insertInProduct.setInt(3,quantity);
        insertInProduct.setInt(4,cost);

        int check = insertInProduct.executeUpdate();

        if(check == 0){
            logger.warning("Some internal error comes.Please insert again");
        }else{
            logger.info("Successfully insert data in Product table");
        }
    }

    /**
     * This function help you to check the user data present or not in table
     * when key find in table, then return true
     * if key doesn't find in the table,then return false;
     * @param id user id
     * @return true /false
     * @throws SQLException exception:
     *                      when you're not connect to database and try to find the key
     */
    public static boolean searchInUser(int id) throws SQLException {
        searchInUer.setInt(1,id);
        ResultSet rs = searchInUer.executeQuery();

        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * This function help you to check the product data present or not in table
     * when key find in table, then return true
     * if key doesn't find in the table,then return false;
     * @param id user id
     * @return true /false
     * @throws SQLException exception:
     *                      when you're not connect to database and try to find the key
     */
    public static boolean searchInProduct(int id) throws SQLException {
        searchInProduct.setInt(1,id);
        ResultSet rs = searchInProduct.executeQuery();

        if(rs.next()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * When you want to print the whole User Table
     * @throws SQLException  Exception :
     *                         when you aren't connect to database and try to fetch data
     */
    public static void printUsers() throws SQLException {
        System.out.println("This is users database table");
        ResultSet  rs = printUsers.executeQuery();

        logger.info ("User id  :  firstName : LastName  : Mobile   :  Address");

        Set<String> set = new HashSet<String>();

        while(rs.next()){
            String details = (rs.getInt(1)+ " : " +rs.getString(2) + " : " + rs.getString(3) +  " : "
                    + rs.getString(4) +  " : " + rs.getString(5));

            set.add(details);
        }
        Iterator<String > iterator = set.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }


    /**
     * When you want to print the whole Product Table
     * @throws SQLException  Exception :
     *                         when you aren't connect to database and try to fetch data
     */
    public static void printProducts() throws SQLException {
        ResultSet rs = printProducts.executeQuery();
        logger.info("Product id  :   name  :  quantity  : price ");

        Set<String> set = new HashSet<String>();
        while (rs.next()){

            String details = (rs.getInt(1) + " : "+rs.getString(2) + " : " + rs.getInt(3) +" : " +rs.getInt(4));
            set.add(details);
        }

        Iterator<String > iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * This function just update the value in database table
     * @param id user id
     * @param firstName user first name
     * @param lastName user last name
     * @param mobile user mobile
     * @param address user address
     * @throws SQLException exception:
     *      *                      when you're not connect with database and try to update
     *      *                      or if the id key is not present in table
     */
    public static void updateUserData(int id,String firstName,String lastName,
                                      String mobile,String address) throws SQLException {
        updateUserData.setString(1,firstName);
        updateUserData.setString(2,lastName);
        updateUserData.setString(3,mobile);
        updateUserData.setString(4,address);
        updateUserData.setInt(5,id);

        int check = updateUserData.executeUpdate();

        /**
         * when executeUpdate is return 0, that means row doesn't enter in it.
         * when executeUpdate is return 1, that means row is successfully insert in table
         */
        if(check == 0){
            logger.warning("Some internal error comes.Please update again");
        }else{
            logger.info("Successfully update data in User table");
        }
    }

    /**
     * This function just update the value in database table
     * @param id    product id
     * @param name product name
     * @param quantity product quantity
     * @param cost product cost;
     * @throws SQLException exception:
     *                      when you're not connect with database and try to update
     *                      or if the id key is not present in table
     */
    public static void updateProductData(int id,String name,int quantity,int cost) throws SQLException {
        updateProductData.setString(1,name);
        updateProductData.setInt(2,quantity);
        updateProductData.setInt(3,cost);

        updateProductData.setInt(4,id);

        int check = updateProductData.executeUpdate();

        /**
         * when executeUpdate is return 0, that means row doesn't enter in it.
         * when executeUpdate is return 1, that means row is successfully insert in table
         */
        if(check == 0){
            logger.warning("Some internal error comes.Please update again");
        }else{
            logger.info("Successfully update data in Product table");
        }
    }

    /**
     * The function delete the entity in the database table
     * @param id    user key
     * @throws SQLException   exception
     *                        when you're not connect to database and try to delete the key
     */
    public static void deleteUser(int id) throws SQLException {
        if(!searchInUser(id)){
            /**
             * check key present in table or not after that delete
             */
            logger.warning("Key not present in table");
            return ;
        }

        deleteUser.setInt(1,id);
        int check = deleteUser.executeUpdate();

        /**
         * when executeUpdate is return 0, that means row doesn't enter in it.
         * when executeUpdate is return 1, that means row is successfully insert in table
         */
        if(check == 0){
            logger.warning("Some internal error comes.Please try again");
        }else{
            logger.info("Successfully delete "+id +" in User table");
        }
    }

    /**
     * The function delete the entity in the database table
     * @param id    product key
     * @throws SQLException   exception
     *                        when you're not connect to database and try to delete the key
     */
    public static void deleteProduct(int id) throws SQLException {
        if(!searchInProduct(id)){
            /**
             * if key is not present in table
             */
            System.out.println("Key isn't present in product table");
            return;
        }

        deleteProduct.setInt(1,id);
        int check = deleteProduct.executeUpdate();

        /**
         * when executeUpdate is return 0, that means row doesn't enter in it.
         * when executeUpdate is return 1, that means row is successfully insert in table
         */
        if(check == 0){
            logger.warning("Some internal error comes.Please try again");
        }else{
            logger.info("Successfully delete "+id +" in User table");
        }
    }


    /**
     * This function help you to order the product.
     * @param order_id  order id
     * @param product_id    product id which user want to purchase
     * @param user_id      user id
     * @param payment       payment is complete ir not, "Done" or "Not"
     * @param order_date    give the date
     * @param dilivery_date     delivery date
     * @param item          number of itme purchase
     * @throws SQLException Exception
     */
    public static void book_product(int order_id,int product_id,int user_id,String payment,
                                    String order_date,String dilivery_date, int item) throws SQLException {
        book_product.setInt(1,order_id);
        book_product.setInt(2,product_id);
        book_product.setInt(3,user_id);
        book_product.setString(4,payment);
        book_product.setString(5,order_date);
        book_product.setString(6,dilivery_date);
        book_product.setInt(7,item);

        int check = book_product.executeUpdate();

        /**
         * when executeUpdate is return 0, that means row doesn't enter in it.
         * when executeUpdate is return 1, that means row is successfully insert in table
         */
        if(check == 0){
            logger.warning("Some internal error comes.Please try again");
        }else{
            logger.info("Successfully order the product in User table");
        }
    }

    /**
     * This function help you to find the Last X orders
     * @param user_id user id
     * @param days days
     * @throws SQLException Exception
     *                      when you're not connect with database and try to access the database
     */
    public static void lastXDayOrder(int user_id,int days) throws SQLException {
        lastXDayOrder.setInt(1,user_id);
        lastXDayOrder.setInt(2,days);
        ResultSet rs = lastXDayOrder.executeQuery();

        while(rs.next()){
            logger.info(rs.getInt(1) + " "+rs.getInt(2) +" "+ rs.getString(4) +
                    " " + rs.getString(5) +" " +rs.getString(6) );
        }
    }

    /**
     * This function help you to get the top X order in user list
     * @param user_id   user id
     * @param top   top order
     * @throws SQLException exception
     */
    public static void topXOrder(int user_id,int top) throws SQLException {
        topXOrder.setInt(1,user_id);
        topXOrder.setInt(2,top);

        /**
         * Fire the Query command and it return resultSet that contain rows.
         */
        ResultSet rs = topXOrder.executeQuery();

        while(rs.next()){
            /**
             * loop run until you fetch all row present in ResultSet
             */
            logger.info(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getString(4) + " " +
                    rs.getString(5) +" "+ rs.getString(6) + " "+rs.getInt(7));
        }
    }

    /**
     * this is main class
     * this function make the connection between program and database
     * with the help of this function we can control the database
     * @param args
     */
    public static void main(String[] args) {
        try {
            /**
             * make the connection between java program and postgres database
             */
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/postgres",
                    "postgres", "root123");

            logger.info("Connection make successfully");

            initialize();

            /**
             * initialize logger
             */
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/logging.properties"));
            logger = Logger.getLogger(E_commerce.class.getName());
            logger.info("logger connect successfully");

            /**
             * try to insert in product table using java
             */
            System.out.print("Enter product id = ");
            int id = in.nextInt();

            System.out.print("Enter product name = ");
            String name = in.next();

            System.out.print("Enter the quantity = ");
            int quantity = in.nextInt();

            System.out.print("Enter the price = ");
            int price = in.nextInt();

            insertInProduct(id,name,quantity,price);

            /**
             * here you can work on database
             */

        }catch(Exception ex){
            logger.warning(ex.getMessage());
        }
    }
}
