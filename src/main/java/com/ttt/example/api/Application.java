package com.ttt.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.ComponentScan;

/**
 * Customer Data Application Spring Boot App.
 */
@ComponentScan("com.ttt.example")
@SpringBootApplication
@SuppressWarnings("checkstyle:hideutilityclassconstructor" /* Spring Boot requires default constructor on Application */)
public class Application
{
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.addListeners(new ApplicationPidFileWriter());
        springApplication.run(args);
    }
}
