package com.zs.hobbies.exception;

/**
 * This class is use for exception handling in my hobbies application
 */
public class InvalidInputException extends RuntimeException{
    int errorCode;
    String errorDescription;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(int errorCode, String errorDescription) {
        this(String.valueOf(errorCode + " " + errorDescription));

        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

}