package com.zs.hobbies.util;

import com.zs.hobbies.exception.ApplicationException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

/**
 * this class is load all external resource
 */
public class ResourceLoader {
    private Properties properties;

    public void loggerManager(){
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/main/resources/logging.properties"));
        }catch (FileNotFoundException e){
            throw new ApplicationException(500,"Sorry, file not present");
        }catch (IOException e){
            throw new ApplicationException(500,"Sorry, input error comes");
        }
    }
    public void loadApplicationResource(){
        try {
            properties = new Properties();
            FileReader fileReader = new FileReader("src/main/resources/application.properties");
            properties.load(fileReader);
        }catch (FileNotFoundException e){
            throw new ApplicationException(500,"Sorry, file not present");
        }catch (IOException e){
            throw new ApplicationException(500,"Sorry, input error comes");
        }
    }

    public Integer getCacheSize() {
       return Integer.parseInt(properties.getProperty("cacheSize"));
    }
}
