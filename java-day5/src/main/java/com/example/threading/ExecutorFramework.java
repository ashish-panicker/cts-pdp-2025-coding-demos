package com.example.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * ExecutorFramework: separates task from thread management
 * java.util.concurrent.Executor interface allows us to submit the task
 * java.util.concurrent.ExecutorService
 * - submit the task as Runnable or as Callable
 * - allows graceful shutdown, forced shutdown
 * - batch operation like invokeAll or invokeAny
 */
public class ExecutorFramework {
    public static void main(String[] args) {
        cachedThreadPool();
    }

    static void cachedThreadPool() {
        try (var executors = Executors.newCachedThreadPool()) {
            task(executors);
        }
    }

    private static void task(ExecutorService executors) {
        for (int i = 0; i < 10; i++) {
            int id = i;
            executors.submit(() -> {
                System.out.println("Task: " + id + " started by Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // Ignore
                }
            });
        }
        executors.shutdown();
    }

    static void fixedThreadPool() {
        try (var executors = Executors.newFixedThreadPool(3)) {
            task(executors);
        }

    }
}
