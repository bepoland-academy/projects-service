package pl.betse.beontime.projectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;

@SpringBootApplication
@LiquibaseDataSource
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
