package test.java.com.zs.hobbies.dto;

import main.java.com.zs.hobbies.dto.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    int id;
    String name;
    String mobile;
    String address;
    Person person ;

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
    }

    @Test
    void getName() {
        assertEquals(name,person.getName());
    }

    @Test
    void setName() {
    }

    @Test
    void getMobile() {
        assertEquals(mobile,person.getMobile());
    }

    @Test
    void setMobile() {
    }

    @Test
    void getAddress() {
        assertEquals(address,person.getAddress());
    }

    @Test
    void setAddress() {

    }
}