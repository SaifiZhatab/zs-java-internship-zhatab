package main.java.com.zs.hobbies.dto;

import main.java.com.zs.hobbies.dao.DataBase;

import java.sql.SQLException;

/**
 * this class provide all person facility
 */
public class Person {
    private int id;
    private String name;
    private String mobile;
    private String address;

    public Person(int id, String name, String mobile, String address) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
    }

    public Person(String name, String mobile, String address) throws SQLException {
        this.id = DataBase.findHigherKey("persons","personid");
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
