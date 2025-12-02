package com.example.collections;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WordCountConcurrentHashMap {
    public static void main(String[] args) throws InterruptedException {

        List<String> list = Arrays.asList(
                "apple banana mango apple",
                "banana apple kiwi mango",
                "mango banana banana apple",
                "kiwi apple banana mango"
        );

        ConcurrentHashMap<String, Integer> wordCount = new ConcurrentHashMap<>();

        Runnable task = () -> {
            for (String line : list) {
                for (String word : line.split("\\s+")) {
                    wordCount.merge(word, 1, Integer::sum);
                }
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();


        System.out.println("Final word count");
        for (var entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

    }
}
