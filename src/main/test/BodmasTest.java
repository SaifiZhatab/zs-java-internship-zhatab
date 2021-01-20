package main.test;

import main.java.com.zs.bodmas.Bodmas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodmasTest {

    Bodmas bodmas ;

    @BeforeEach
    void setUp() {
        bodmas = new Bodmas();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sum() {
        assertEquals(5,bodmas.sum(3,2));
    }

    @Test
    void subtract() {
        assertEquals(5,bodmas.subtract(10,5));
    }

    @Test
    void multiply() {
    }

    @Test
    void divide() {
    }

    @Test
    void power() {
    }

    @Test
    void precedence() {
    }

    @Test
    void perform() {
    }

    @Test
    void bodmasSolver() {
        assertEquals(15 , bodmas.bodmasSolver("1+7*2"));
    }
}