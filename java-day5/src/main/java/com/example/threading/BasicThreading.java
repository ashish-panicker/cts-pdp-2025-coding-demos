package com.example.threading;

// What is a Process?
// - A program in execution, has its own memory, managed by OS, heavyweight unit
// - A process cannot directly access another process memory
// What are Threads?
// - A part of a process that can execute independently, shares memory
// Process vs Threads?
public class BasicThreading {
    public static void main(String[] args) {
        System.out.println("Main Thread Starting...");
        new Thread(new MyRunnable()).start();
        new MyThread().start();
        System.out.println("Main Thread Exiting...");

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Lambda thread:" + i);
            }
        }).start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("MyThread:" + i);
        }
    }
}