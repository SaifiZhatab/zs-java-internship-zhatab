package com.zs.hobbies.exception;

public class ApplicationException extends RuntimeException{
    private int errorCode;
    private String errorDescription;

    public ApplicationException(int errorCode, String errorDescription) {
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
