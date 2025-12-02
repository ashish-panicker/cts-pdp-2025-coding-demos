package com.example.threading;

import java.util.concurrent.Executors;

public class CpuLoadTestWithTPE {
    public static void main(String[] args) {
        int coreCount = Runtime.getRuntime().availableProcessors();
        int fibTarget = 40;
        long startTime = System.currentTimeMillis();

        try (var pool = Executors.newFixedThreadPool(coreCount)) {
            for (int i = 0; i < coreCount; i++) {
                int taskId = i + 1;
                pool.submit(() -> {
                    System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
                    long result = CpuLoader.fib(fibTarget);
                    System.out.println("Task " + taskId + " finished: Fib result = " + result);
                });
            }
            pool.shutdown();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }
}
