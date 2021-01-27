package main.java.com.zs.hobbies.dto;

import java.sql.SQLException;

/**
 * this class provide all person facility
 */
public class Person {
    private int id;
    private String name;
    private String mobile;
    private String address;

    /**
     *if the user give id by itself
     * @param id the user id
     * @param name  name of user
     * @param mobile mobile number string
     * @param address address of user
     */
    public Person(int id, String name, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    /**
     * if the user doesn't give id, then it take automatically
     * @param name  name of user
     * @param mobile mobile number string
     * @param address address of user
     */
    public Person(String name, String mobile, String address) throws SQLException {
        this.id = -1;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
