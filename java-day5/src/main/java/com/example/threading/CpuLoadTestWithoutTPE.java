package com.example.threading;

public class CpuLoadTestWithoutTPE {
    public static void main(String[] args) throws InterruptedException {
        int tasks = Runtime.getRuntime().availableProcessors();
        int fibTarget = 40;
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[tasks];
        System.out.println("Tasks to be scheduled: " + tasks);
        for (int i = 0; i < tasks; i++) {
            int taskId = i + 1;
            threads[i] = new Thread(() -> {
                System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
                long result = CpuLoader.fib(fibTarget);
                System.out.println("Task " + taskId + " finished: Fib result = " + result);
            });
            threads[i].start();
        }

        for (var t: threads) {
            t.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime) + "ms");
    }
}
