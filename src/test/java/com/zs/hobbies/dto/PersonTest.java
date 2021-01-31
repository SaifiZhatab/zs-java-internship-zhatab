package com.zs.hobbies.dto;

import com.zs.hobbies.dto.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getId() {
        assertEquals(id,person.getId());
    }

    @Test
    void setId() {
        person.setId(2);
        assertEquals(2,person.getId());
    }

    @Test
    void getName() {
        assertEquals(name,person.getName());
    }

    @Test
    void setName() {
        person.setName("Aftab");
        assertEquals("Aftab",person.getName());
    }

    @Test
    void getMobile() {
        assertEquals(mobile,person.getMobile());
    }

    @Test
    void setMobile() {
        person.setMobile("8279575493");
        assertEquals("8279575493",person.getMobile());
    }

    @Test
    void getAddress() {
        assertEquals(address,person.getAddress());
    }

    @Test
    void setAddress() {
        person.setAddress("MP");
        assertEquals("MP",person.getAddress());
    }
}