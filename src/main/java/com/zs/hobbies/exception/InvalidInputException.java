package main.java.com.zs.hobbies.exception;

public class InvalidInputException extends RuntimeException{
    int errorCode;
    String errorDescription;

    public InvalidInputException(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getMessage(){
        return  (errorCode + " " + errorDescription);
    }
}