package com.example.threading;

import java.util.concurrent.*;

// Thread State and Lifecycle
// New:             When a thread is created
// Runnable:        Ready to run, scheduler can pick it up for execution
// Running:         Thread is executing
// Blocked:         Waiting for a lock
// Waiting:         When a wait() or join() method is called, waiting without a timeout
// Timed Waiting:   When wait() is called with a time parameter
// Terminated:      When the Thread has completed execution successfully or with exception
public class ThreadLifeCycle {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("Main thread started");
        timedWaiting();
        System.out.println("Main thread completed.");
    }

    static void timedWaiting() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("[t1] started");
            try {
                Thread.sleep(2000);
                System.out.println("[t1] completed.");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });

        Thread t2 = new Thread(() -> {
            System.out.println("[t2] Started, will join t1 for max 1500ms");
            try {
                t1.join(1500);
                System.out.println("[t2] join(1500) returned");
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            System.out.println("[t2] Finished run()");
        });

        System.out.println("[main] Starting sleeper...");
        t1.start();

        Thread.sleep(50);

        System.out.println("[main] Starting t...");
        t2.start();

        // give t time to start join()
        Thread.sleep(150);
        System.out.println("[main] State of t2 after 150ms: " + t2.getState());

        t2.join();
        t1.join();

        System.out.println("[main] Final state of t2: " + t2.getState());
    }

    /*
     * Every shared object has a wait-set and entry-list
     * entry-list:
     * - any thread trying to acquire a lock on the shared object is placed inside this
     * - if a thread was notified via notify() or notifyAll()
     * - threads are in BLOCKED state
     * - threads that are ready to acquire the lock are placed here
     *
     * wait-set
     * - threads that have called the wait() method
     * - they have released the lock
     * - threads are in WAITING or TIMED_WAITING state
     */
    static void waitingStateDemoWithThreeThreads() throws InterruptedException {
        Object sharedObject = new Object();
        Runnable task = () -> {
            var name =  Thread.currentThread().getName();
            synchronized (sharedObject) {
                System.out.println(name + " has acquired lock, calling wait()...");
                try {
                    /*
                     * 1. Release the lock on sharedObject
                     * 2. Move the thread into wait-set of shared object
                     * 3. Thread states becomes WAITING
                     */
                    sharedObject.wait();

                    // After notify
                    System.out.println(name + " has awakened and re-acquired the lock.");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            System.out.println(name + " has finished execution.");
        }; // End Runnable

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);

        t1.start(); t2.start(); t3.start();

        // entry-list:  []
        // wait-set:    [T1, T2, T3]

        // Main thread sleeps
        Thread.sleep(500);

        System.out.println("State of threads after wait():");
        System.out.println("T1: " + t1.getState());
        System.out.println("T2: " + t2.getState());
        System.out.println("T3: " + t3.getState());
        System.out.println();

        synchronized (sharedObject) {
            System.out.println("main() has acquired lock, calling notify()...");
            sharedObject.notify();
            System.out.println("State of threads after notify():");
            System.out.println("T1: " + t1.getState());
            System.out.println("T2: " + t2.getState());
            System.out.println("T3: " + t3.getState());
            Thread.sleep(1000);
            System.out.println("main() has finished synchronized.");
        }
        // entry-list:  [T!]
        // wait-set:    [T2, T3]
        System.out.println();

        Thread.sleep(500);
        System.out.println("State of threads after notify() completion:");
        System.out.println("T1: " + t1.getState());
        System.out.println("T2: " + t2.getState());
        System.out.println("T3: " + t3.getState());
        System.out.println();

        synchronized (sharedObject) {
            System.out.println("main() has acquired lock again, calling notifyAll()...");
            sharedObject.notifyAll();
            System.out.println("State of threads after notifyAll():");
            System.out.println("T1: " + t1.getState());
            System.out.println("T2: " + t2.getState());
            System.out.println("T3: " + t3.getState());
            System.out.println("main() has finished synchronized.");
        }
        // entry-list:  [T2, T3]
        // wait-set:    []
        System.out.println();

        t1.join(); t2.join(); t3.join();

        System.out.println("Final State of threads:");
        System.out.println("T1: " + t1.getState());
        System.out.println("T2: " + t2.getState());
        System.out.println("T3: " + t3.getState());

        System.out.println();

    }

    static void waitingStateDemo() throws InterruptedException {
        Object sharedObject = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (sharedObject) { // has acquired lock on sharedObject
                try {
                    System.out.println("T1 thread started, going to call wait()");
                    // release the lock that it has acquired on sharedObject
                    // the t1 will go into WAITING state
                    // this will go back into RUNNABLE once notify() or notifyAll() is called
                    sharedObject.wait(); // pause the execution here
                    System.out.println("T1 thread awakened.");
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        t1.start();
        Thread.sleep(200);
        System.out.println("State of thread [Waiting]:" + t1.getState());
        synchronized (sharedObject) { // lock is acquired by: main
            // Picks one thread from the objects waiting threads, moves it into WAITING or RUNNABLE
            // Woken thread can only continue after the exit of synchronized block
            sharedObject.notify();
            System.out.println("State of thread [Waiting]:" + t1.getState());
            Thread.sleep(2000);
        } // release the lock here
        t1.join();
    }

    static void blockedStateDemo() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    System.out.println("Thread 1 lock acquired.");
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2 lock acquired.");
            }
        });

        t1.start();
        // main thread sleeps, not t1, idea is to Delay the start of t2
        Thread.sleep(100);

        t2.start();
        Thread.sleep(100);

        System.out.println("t2 status is " + t2.getState());

        t1.join(); // waits for t1 to complete
        t2.join(); // waits for t2 to complete
    }

    static void simpleStateDemo() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        });

        System.out.println("State of thread [After Creation]:" + t1.getState());

        t1.start(); // Always goes into RUNNABLE state
        System.out.println("State of thread [After start()]:" + t1.getState());

        Thread.sleep(100);
        System.out.println("State of thread [After sleep()]:" + t1.getState());

        t1.join();
        System.out.println("State of thread [After join()]:" + t1.getState());
    }
}
