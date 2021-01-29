package com.zs.hobbies.util;

import com.zs.hobbies.exception.InvalidInputException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

public class ResourceLoader {
    private static Properties properties;

    public static void loggerManager(){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/logging.properties"));
        }catch (FileNotFoundException e){
            throw new InvalidInputException(400,"Sorry, file not present");
        }catch (IOException e){
            throw new InvalidInputException(400,"Sorry, input error comes");
        }
    }
    public static void loadApplicationResource(){
        try {
            properties = new Properties();
            FileReader fileReader = new FileReader("src/main/resources/application.properties");
            properties.load(fileReader);
        }catch (FileNotFoundException e){
            throw new InvalidInputException(400,"Sorry, file not present");
        }catch (IOException e){
            throw new InvalidInputException(400,"Sorry, input error comes");
        }
    }

    public static  String getParameter(String name) {
       return properties.getProperty(name);
    }
}
