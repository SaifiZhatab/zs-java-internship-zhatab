package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HobbyTest {

    private int id;
    private Hobby hobby;

    @BeforeEach
    void setUp() {
        id = 5;
        hobby = new Hobby(5);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getId() {
        assertEquals(id,hobby.getId());
    }

    /**
     * check this function is set the correct value or not
     */
    @Test
    void setId() {
        hobby.setId(2);

        /**
         * check the expected or actual value is same
         */
        assertEquals(2,hobby.getId());
    }
}