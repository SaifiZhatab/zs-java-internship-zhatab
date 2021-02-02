package com.zs.e_commerce;

import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * This class help you to interact with e_commerce database
 */
public class E_commerce {
    static Connection con;
    private static Logger logger;

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
    static void insertInUsers(int id,String firstName,String lastName,
                                     String mobile,String address) throws SQLException {

        PreparedStatement insertInUser = con.prepareStatement("insert into users values (?,?,?,?,?)");

        if(searchInUser(id)){
            logger.info("Key already present in user table ");
            return ;
        }
        insertInUser.setInt(1,id);
        insertInUser.setString(2,firstName);
        insertInUser.setString(3,lastName);
        insertInUser.setString(4,mobile);
        insertInUser.setString(5,address);

        try{
            insertInUser.executeUpdate();
            logger.info("Successfully insert data in User table");
        }catch (SQLException ex) {
            logger.warning("Some internal error comes.Please insert again");
        }

        insertInUser.close();
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
    static void insertInProduct(int id,String name,int quantity,int cost) throws SQLException {
        PreparedStatement insertInProduct = con.prepareStatement("insert into product values (?,?,?,?)");

        insertInProduct.setInt(1,id);
        insertInProduct.setString(2,name);
        insertInProduct.setInt(3,quantity);
        insertInProduct.setInt(4,cost);

        try{
            insertInProduct.executeUpdate();
            logger.info("Successfully insert data in Product table");
        }catch (SQLException ex) {
            logger.warning("Some internal error comes.Please insert again");
        }
        insertInProduct.close();
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
    static boolean searchInUser(int id) throws SQLException {
        PreparedStatement searchInUer = con.prepareStatement("select * from users where id = ?");

        searchInUer.setInt(1,id);
        ResultSet rs = searchInUer.executeQuery();

        searchInUer.close();
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
    static boolean searchInProduct(int id) throws SQLException {
        PreparedStatement searchInProduct = con.prepareStatement("select * from product where id = ?");

        searchInProduct.setInt(1,id);
        ResultSet rs = searchInProduct.executeQuery();
        searchInProduct.close();

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
    static void printUsers() throws SQLException {
        PreparedStatement printUsers = con.prepareStatement("select * from users");

        logger.info("This is users database table");
        ResultSet  rs = printUsers.executeQuery();

        logger.info ("User id  :  firstName : LastName  : Mobile   :  Address");


        while(rs.next()){
            logger.info(rs.getInt("user_id")+ " : " +rs.getString("fisrtName") + " : "
                    + rs.getString("lastName") +  " : " + rs.getString("mobile") +  " : "
                    + rs.getString("address"));
        }
        printUsers.close();
    }


    /**
     * When you want to print the whole Product Table
     * @throws SQLException  Exception :
     *                         when you aren't connect to database and try to fetch data
     */
    static void printProducts() throws SQLException {
        PreparedStatement printProducts = con.prepareStatement("select * from product");
        ResultSet rs = printProducts.executeQuery();
        logger.info("Product id  :   name  :  quantity  : price ");


        while (rs.next()){
            logger.info (rs.getInt("product_id") + " : "+rs.getString("name") + " : "
                    + rs.getInt("quantiy") +" : " +rs.getInt("price"));
        }
        printProducts.close();
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
    static void updateUserData(int id,String firstName,String lastName,
                                      String mobile,String address) throws SQLException {
        PreparedStatement  updateUserData = con.prepareStatement("update users set fisrtname=? , lastname = ?,mobile=?,address=? where id = ?");

        updateUserData.setString(1,firstName);
        updateUserData.setString(2,lastName);
        updateUserData.setString(3,mobile);
        updateUserData.setString(4,address);
        updateUserData.setInt(5,id);

       try {
           updateUserData.executeUpdate();
           logger.info("Successfully update data in User table");
       }catch (SQLException ex) {
           logger.warning("Some internal error comes.Please update again");
       }
       updateUserData.close();
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
    static void updateProductData(int id,String name,int quantity,int cost) throws SQLException {
        PreparedStatement updateProductData = con.prepareStatement("update product set name=?, quantity=?,price=? where id=?");

        updateProductData.setString(1,name);
        updateProductData.setInt(2,quantity);
        updateProductData.setInt(3,cost);

        updateProductData.setInt(4,id);

       try{
           updateProductData.executeUpdate();
           logger.info("Successfully update data in Product table");
       }catch (SQLException ex) {
           logger.warning("Some internal error comes.Please update again");
       }
       updateProductData.close();
    }

    /**
     * The function delete the entity in the database table
     * @param id    user key
     * @throws SQLException   exception
     *                        when you're not connect to database and try to delete the key
     */
    static void deleteUser(int id) throws SQLException {
        PreparedStatement deleteUser = con.prepareStatement("delete from users where id = ?");

        /**
         * check key present in table or not after that delete
         */
        if(!searchInUser(id)){
            logger.warning("Key not present in table");
            return ;
        }

        deleteUser.setInt(1,id);
        try{
            deleteUser.executeUpdate();
            logger.info("Successfully delete "+id +" in User table");
        }catch (SQLException ex) {
            logger.warning("Some internal error comes.Please try again");
        }
       deleteUser.close();
    }

    /**
     * The function delete the entity in the database table
     * @param id    product key
     * @throws SQLException   exception
     *                        when you're not connect to database and try to delete the key
     */
    static void deleteProduct(int id) throws SQLException {
        PreparedStatement deleteProduct = con.prepareStatement("delete from product where id = ?");

        /**
         * if key is not present in table
         */
        if(!searchInProduct(id)){
            logger.info("Key isn't present in product table");
            return;
        }

        deleteProduct.setInt(1,id);
        try{
            deleteProduct.executeUpdate();
            logger.info("Successfully delete "+id +" in User table");
        }catch (SQLException ex) {
            logger.warning("Some internal error comes.Please try again");
        }
        deleteProduct.close();
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
    static void book_product(int order_id,int product_id,int user_id,String payment,
                                    String order_date,String dilivery_date, int item) throws SQLException {
       PreparedStatement book_product = con.prepareStatement("insert into orders values (?,?,?,?,?,?,?)");

        book_product.setInt(1,order_id);
        book_product.setInt(2,product_id);
        book_product.setInt(3,user_id);
        book_product.setString(4,payment);
        book_product.setString(5,order_date);
        book_product.setString(6,dilivery_date);
        book_product.setInt(7,item);

       try{
           book_product.executeUpdate();
           logger.info("Successfully order the product in User table");
       }catch (SQLException ex) {
           logger.warning("Some internal error comes.Please try again");
       }
       book_product.close();
    }

    /**
     * This function help you to find the Last X orders
     * @param user_id user id
     * @param days days
     * @throws SQLException Exception
     *                      when you're not connect with database and try to access the database
     */
    static void lastXDayOrder(int user_id,int days) throws SQLException {
        PreparedStatement  lastXDayOrder = con.prepareStatement("select * from order where user_id = ? order by order_date desc limit ?");

        lastXDayOrder.setInt(1,user_id);
        lastXDayOrder.setInt(2,days);
        ResultSet rs = lastXDayOrder.executeQuery();

        while(rs.next()){
            logger.info(rs.getInt("product_id") + " " + rs.getInt("user_id") + " "
                    + rs.getString("payment") + " " + rs.getString("order_date") + " "
                    + rs.getString("dilivery_date"));
        }
        lastXDayOrder.close();
    }

    /**
     * This function help you to get the top X order in user list
     * @param user_id   user id
     * @param top   top order
     * @throws SQLException exception
     */
    static void topXOrder(int user_id,int top) throws SQLException {
        PreparedStatement topXOrder = con.prepareStatement("select * from orders where user_id = ? order by item desc limit ?");

        topXOrder.setInt(1,user_id);
        topXOrder.setInt(2,top);

        /**
         * Fire the Query command and it return resultSet that contain rows.
         */
        ResultSet rs = topXOrder.executeQuery();

        while(rs.next()){
            logger.info(rs.getInt("product_id") + " " + rs.getString("payment") + " "
            + rs.getString("order_date"));
        }
        topXOrder.close();
    }

    /**
     * this is main class
     * this function make the connection between program and database
     * with the help of this function we can control the database
     * @param args
     */
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        try {
//            /**
//             * make the connection between java program and postgres database
//             */
//            Class.forName("org.postgresql.Driver");
//            con = DriverManager.getConnection("jdbc:postgresql://0.0.0.0:2006/postgres",
//                    "postgres", "root123");
//
//            logger.info("Connection make successfully");
//
//
//            /**
//             * initialize logger
//             */
//                logger = Logger.getLogger(E_commerce.class.getName());
//            logger.info("logger connect successfully");
//
//            /**
//             * try to insert in product table using java
//             */
//            logger.info("Enter product id = ");
//            int id = in.nextInt();
//
//            logger.info("Enter product name = ");
//            String name = in.next();
//
//            logger.info("Enter the quantity = ");
//            int quantity = in.nextInt();
//
//            logger.info("Enter the price = ");
//            int price = in.nextInt();
//
//            insertInProduct(id,name,quantity,price);
//
//            /**
//             * here you can work on database
//             */
//
//        }catch(Exception ex){
//            logger.warning(ex.getMessage());
//        }
//    }
}
