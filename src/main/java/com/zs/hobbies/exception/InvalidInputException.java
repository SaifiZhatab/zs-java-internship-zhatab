package main.java.com.zs.hobbies.exception;

/**
 * This class is use for exception handling in my hobbies application
 */
public class InvalidInputException extends RuntimeException{
    int errorCode;
    String errorDescription;

    public InvalidInputException(int errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    /**
     * This function override the Exception getMessage() method
     * @return the string message
     */
    public String getMessage(){
        return  (errorCode + " " + errorDescription);
    }
}