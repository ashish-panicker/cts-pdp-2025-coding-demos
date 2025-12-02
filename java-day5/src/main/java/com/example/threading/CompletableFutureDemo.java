package com.example.threading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class CompletableFutureDemo {
    public static void main(String[] args) {
//        CompletableFuture.runAsync(() -> System.out.println("Hello World run!"));
//        CompletableFuture.supplyAsync(() -> "Hello World!")
//                            .thenApply(String::toUpperCase)
//                            .thenAccept(System.out::println);
//        CompletableFuture.supplyAsync(() -> {
//                    System.out.println(Thread.currentThread().getName());
//                    return "Hello World!";
//                })
//                .thenApplyAsync(String::toUpperCase)
//                .thenAcceptAsync((s) -> {
//                    System.out.println(Thread.currentThread().getName());
//                    System.out.println(s);
//                });

        CompletableFuture<Integer> cf =
                CompletableFuture.supplyAsync(
                                () -> {
                                    if (ThreadLocalRandom.current().nextBoolean()) {
                                        throw new RuntimeException("Some error");
                                    }
                                    return ThreadLocalRandom.current().nextInt();
                                })
                        .handle((value, ex) -> {
                            if (ex == null) {
                                System.out.println(value);
                            }
                            return -1;
                        }).thenApply(v -> v * 2)
                        .exceptionally(ex -> 0)
                        .whenComplete((v, ex) -> {
                            System.out.println("Final = " + v);
                        });
    }
}
