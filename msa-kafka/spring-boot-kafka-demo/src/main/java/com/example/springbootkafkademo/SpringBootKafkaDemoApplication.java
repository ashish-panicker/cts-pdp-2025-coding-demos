package com.example.springbootkafkademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Use case
 * Order Events System
 * Producer: OrderService publisher OrderCreatedEvent
 * Consumer: Notification Service consumes Order Events
 * Events are Java -> JSON -> Java
 * Multiple consumers for concurrency and consumer groups
 */
@SpringBootApplication
public class SpringBootKafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKafkaDemoApplication.class, args);
    }

}
