package com.zs.hobbies.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HobbyTest {

    private int id;
    private Hobby hobby;

    @BeforeEach
    void setUp() {
        id = 5;
        hobby = new Hobby(5);
    }

    @Test
    void getId() {
        assertEquals(id,hobby.getId());
    }

    @Test
    void setId() {
        hobby.setId(2);
        assertEquals(2,hobby.getId());
    }
}