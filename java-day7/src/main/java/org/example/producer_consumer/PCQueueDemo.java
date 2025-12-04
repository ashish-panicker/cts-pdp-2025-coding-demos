package org.example.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

/* Producer puts data into a shared buffer
 * Consumer removes the item from that buffer
 * Rules:
 * - Producer will wait if the buffer is full
 * - Consumer must wait if the buffer is empty
 * - Coordination must avoid race conditions
 */
public class PCQueueDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(new Producer(buffer));
        Thread consumer = new Thread(new Consumer(buffer));

        producer.start();
        consumer.start();
    }
}

class SharedBuffer {

    private final Queue<Integer> q = new LinkedList<>();
    private final int CAPACITY = 6;

    public synchronized void produce(int item) throws InterruptedException {
        while (q.size() == CAPACITY) {
            wait();
        }
        q.add(item);
        System.out.println("Item produced: " + item);
        notifyAll();
    }

    public synchronized int consume() throws InterruptedException {
        while (q.isEmpty()) {
            wait();
        }
        int item = q.remove();
        System.out.println("Consumed: " + item);
        notifyAll();
        return item;
    }
}

class Producer implements Runnable {

    private final SharedBuffer buffer;

    Producer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.produce(i);
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {

    private final SharedBuffer buffer;

    Consumer(SharedBuffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            try {
                buffer.consume();
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

