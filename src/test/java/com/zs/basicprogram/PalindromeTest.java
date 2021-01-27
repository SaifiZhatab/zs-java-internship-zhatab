package main.test;

import com.zs.basicprogram.Palindrome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PalindromeTest {

    Palindrome pd;

    @BeforeEach
    void setUp() {
        pd = new Palindrome();
    }

    @Test
    void palindrome() {
        assertEquals(true , pd.palindrome(15551));
        assertEquals(false , pd.palindrome(12345));
    }
}