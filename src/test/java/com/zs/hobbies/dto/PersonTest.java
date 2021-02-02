package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This is a testing class for Person dto class
 */
class PersonTest {

    private int id;
    private String name;
    private String mobile;
    private String address;
    private Person person ;

    @BeforeEach
    void setUp() {
        id = 4;
        name = "zhatab";
        mobile = "8010311757";
        address = "gzb";
        person = new Person(id,name,mobile,address);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getId() {
        assertEquals(id,person.getId());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setId() {
        person.setId(2);

        /**
         * check the expected or actual value is same
         */
        assertEquals(2,person.getId());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getName() {
        assertEquals(name,person.getName());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setName() {
        person.setName("Aftab");

        /**
         * check the expected or actual value is same
         */
        assertEquals("Aftab",person.getName());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getMobile() {
        assertEquals(mobile,person.getMobile());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setMobile() {
        person.setMobile("8279575493");

        /**
         * check the expected or actual value is same
         */
        assertEquals("8279575493",person.getMobile());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getAddress() {
        assertEquals(address,person.getAddress());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setAddress() {
        person.setAddress("MP");

        /**
         * check the expected or actual value is same
         */
        assertEquals("MP",person.getAddress());
    }
}