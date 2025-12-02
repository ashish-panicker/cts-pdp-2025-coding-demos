package com.example.threading;

import java.util.concurrent.*;

public class CallableFutureDemo {
    public static void main(String[] args) {
        try (ExecutorService pool = Executors.newSingleThreadExecutor()) {

            Callable<String> helloWorldTask = () -> {
                System.out.println("hello world");
                return "hello world";
            };

            /*
             * Future
             * - Submit a task
             * - Check periodically if it is done, using isDone()
             * - if you really want the result call the get()
             */
            Future<String> future = pool.submit(helloWorldTask);
            System.out.println("Task submitted");

            while (!future.isDone()) {
                System.out.println("Waiting for task");
                Thread.sleep(1000);
            }

            // get() is a blocking operation
            System.out.println("Task done: " + future.get());

            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
