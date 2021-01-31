package com.zs.hobbies.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getErrorCode() {
        assertEquals(errorCode,invalidInputException.getErrorCode());
    }

    @Test
    void getErrorDescription() {
        assertEquals(errorDescription,invalidInputException.getErrorDescription());
    }
}