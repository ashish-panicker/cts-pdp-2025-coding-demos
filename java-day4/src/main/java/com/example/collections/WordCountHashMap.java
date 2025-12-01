package com.example.collections;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class WordCountHashMap {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = Arrays.asList(
                "apple banana mango apple",
                "banana apple kiwi mango",
                "mango banana banana apple",
                "kiwi apple banana mango"
        );

        Map<String, Integer> wordCount = Collections.synchronizedMap(new HashMap<>());
        ReentrantLock lock = new ReentrantLock();

        Runnable task = () -> {
            for (String line : list) {
                for (String word : line.split("\\s+")) {
//                    synchronized (wordCount) {
                    lock.lock();
                    Integer oldValue = wordCount.get(word);
                    if (oldValue == null) {
                        wordCount.put(word, 1);
                    } else {
                        wordCount.put(word, oldValue + 1);
                    }
                    lock.unlock();
//                    }
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
