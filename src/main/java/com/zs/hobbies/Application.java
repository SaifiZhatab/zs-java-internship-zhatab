package com.zs.hobbies;

import com.zs.hobbies.util.DataBase;
import com.zs.hobbies.util.ResourceLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class Application {
    public static void main(String st[]) {
        ResourceLoader resourceLoader = new ResourceLoader();
        resourceLoader.loadApplicationResource();
        resourceLoader.loggerManager();
        DataBase dataBase = new DataBase();

        SpringApplication.run(Application.class, st);
    }
}

/**
 * http://localhost:8082/swagger-ui.html#/ this is swagger UI web site where you can check the mappings
 */