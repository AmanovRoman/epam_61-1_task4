package com.epam.spring.hometask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Program: Movie Theater manager + database.
 *
 * @author Roman Amanov
 * <p>
 * Database used: MySql 8
 * Properties for database connection stored in `database.properties`
 * Please use 'resources/DatabaseStructure.sql' for recreating DB structure
 * <p>
 * Notes:
 * Use Spring-shell`s 'auto' command for prefill automatically DB
 * Use Spring-shell`s 'clear all' command for delete all data from DB
 * Use Spring-shell`s 'info event' & 'info discount' commands for see information
 * Use Spring-shell`s 'help' command for examine all available shell methods
 */

@SpringBootApplication
public class MovieTheaterManager {

    public static void main(String[] args) {
        System.out.println(
                "Program: Movie Theater manager + database (DB).\n" +
                        " * @Author Roman Amanov\n" +
                        " * \n" +
                        " * Database used: MySql 8\n" +
                        " * Properties for database connection stored in `database.properties`\n" +
                        " * Please use 'resources/DatabaseStructure.sql' for recreating DB structure\n" +
                        " * \n" +
                        " * Notes:\n" +
                        " * Use Spring-shell`s 'auto' command for prefill DB automatically\n" +
                        " * Use Spring-shell`s 'clear all' command for delete all data from DB\n" +
                        " * Use Spring-shell`s 'info event' & 'info discount' commands for see information" +
                        " * Use Spring-shell`s 'help' command for examine all available shell methods"
        );
        SpringApplication.run(MovieTheaterManager.class, args);
    }
}
