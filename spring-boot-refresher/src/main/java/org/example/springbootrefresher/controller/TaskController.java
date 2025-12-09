package org.example.springbootrefresher.controller;

import org.example.springbootrefresher.service.DbBgTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    private final DbBgTaskExecutor executor;

    public TaskController(DbBgTaskExecutor executor) {
        this.executor = executor;
    }

    @GetMapping("/tasks/bg")
    public String bgTask() {
        executor.fileCleanup();
        return "task done";
    }
}
