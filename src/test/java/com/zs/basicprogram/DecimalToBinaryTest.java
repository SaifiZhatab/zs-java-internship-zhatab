package main.test;

import com.zs.basicprogram.DecimalToBinary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecimalToBinaryTest {

    DecimalToBinary dtb;

    @BeforeEach
    void setUp() {
        dtb = new DecimalToBinary();
    }

    @Test
    void binaryToDecimal() {
        assertEquals("1010",dtb.binaryToDecimal(10));
    }
}