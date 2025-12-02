package com.example.threading;

import java.util.concurrent.*;

public class CallableFutureAnotherDemo {
    public static void main(String[] args) {

        Callable<String> task = () -> {
            if (ThreadLocalRandom.current().nextBoolean()) {
                throw new RuntimeException("Some error");
            }
            TimeUnit.SECONDS.sleep(1);
            return "Completed";
        };
        Future<String> future = null;
        try (var pool = Executors.newFixedThreadPool(3)) {
            future = pool.submit(task);
            String result = future.get(2, TimeUnit.SECONDS);
            System.out.println(result);
            pool.shutdown();
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            System.err.println(e.getMessage());
            future.cancel(true);
            Thread.currentThread().interrupt();
        }

    }
}
