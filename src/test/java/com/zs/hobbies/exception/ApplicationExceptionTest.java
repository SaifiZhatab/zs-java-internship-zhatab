package com.zs.hobbies.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class is Application exception test implementation
 */
class ApplicationExceptionTest {

    private int errorCode;
    private String errorDescription;
    private ApplicationException applicationException;

    @BeforeEach
    void setUp() {
        errorCode = 400;
        errorDescription = "input error";

        applicationException = new ApplicationException(errorCode,errorDescription);
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getErrorCode() {
        assertEquals(errorCode,applicationException.getErrorCode());
    }

    /**
     * check this function in dto return correct value or not
     */
    @Test
    void getErrorDescription() {
        assertEquals(errorDescription,applicationException.getErrorDescription());
    }
}