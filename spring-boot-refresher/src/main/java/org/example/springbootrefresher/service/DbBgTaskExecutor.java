package org.example.springbootrefresher.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class DbBgTaskExecutor {

    @Async("bgExecutor")
    public void fileCleanup() {
        System.err.println("Started working in async mode: " + Thread.currentThread().getName() +
                " Started at: " + LocalTime.now());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.err.println("Completed working in async mode: " + Thread.currentThread().getName() +
                " Started at: " + LocalTime.now());
    }
}
