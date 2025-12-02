package com.example.threading;

/*
 * Thread Interruption not means forcefully stop threads, its is a way requesting a thread
 * to stop what it is doing. This is done with the help of interrupt flag.
 *
 * interrupt()      -> set the interrupt flag
 * isInterrupted()  -> check the interrupt flag
 * interrupted()    -> clears the flag
 *
 * What happens when a thread is interrupted?
 * Case 1: Thread is in sleep/wait/join
 * - throw InterruptedException
 * - clear the interrupt flag
 * Case 2: Thread is running normally
 * - Set the interrupt flag
 * - thread has to check for the flag and stop the execution
 */
public class ThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        interruptWaitingThread();
    }

    static void interruptWaitingThread() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // Do now swallow this, handle and exit gracefully
                    System.out.println("Thread: "  + Thread.currentThread().getName() + " interrupted while waiting.");
                    System.out.println("T1 state[After Interrupt]: " +  Thread.currentThread().getState()); // RUNNABLE
                }
            }
        });
        t1.start();
        Thread.sleep(1000);

        System.out.println("Thread: " + Thread.currentThread().getName() + "interrupting t1.");
        System.out.println("T1 state [Before Interrupt]: " + t1.getState());
        /*
         * When interrupt is called on a waiting thread
         * - wake up the waiting thread
         * - throw the InterruptedException
         * - let the thread resume execution
         */
        t1.interrupt();

        t1.join();
        System.out.println("T1 state [Final]: " + t1.getState());
    }

    static void interruptRunningThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName() + ", Value: " + Math.floor(Math.random() * 1000));
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread: " + Thread.currentThread().getName() + " interrupted, exiting...");
                    break;
                }
            }
        });
        t1.start();
        Thread.sleep(1000);
        System.out.println("Thread: " + Thread.currentThread().getName() + "interrupting t1.");
        t1.interrupt();
    }

    static void interruptSleepingThread() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + " going to sleep.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                System.out.println("Thread: " + Thread.currentThread().getName() + " interrupted.");
                return;
            }
            System.out.println("Thread: " + Thread.currentThread().getName() + " woke up without interruption.");
        });
        t1.start();
        Thread.sleep(1000);
        System.out.println("Thread: " + Thread.currentThread().getName() + " interrupting t1.");
        t1.interrupt();
    }
}
