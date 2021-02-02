package com.zs.hobbies.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is InvalidInput exception test implementation
 */
class InvalidInputExceptionTest {
    private int errorCode;
    private String errorDescription;
    private InvalidInputException invalidInputException;

    @BeforeEach
    void setUp() {
        errorCode = 400;
        errorDescription = "input error";

        invalidInputException = new InvalidInputException(errorCode,errorDescription);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getErrorCode() {
        assertEquals(errorCode,invalidInputException.getErrorCode());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getErrorDescription() {
        assertEquals(errorDescription,invalidInputException.getErrorDescription());
    }
}