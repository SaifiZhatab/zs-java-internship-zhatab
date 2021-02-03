package com.zs.hobbies.exception;

/**
 * This class is use for exception handling in my hobbies application
 */
public class InvalidInputException extends RuntimeException{
    private int errorCode;
    private String errorDescription;

    public InvalidInputException(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public int getErrorCode(){
        return errorCode;
    }

    public String getErrorDescription(){
        return errorDescription;
    }

}