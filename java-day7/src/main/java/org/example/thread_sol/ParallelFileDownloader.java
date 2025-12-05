package org.example.thread_sol;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 *
 * Challenge 1 — Parallel File Downloader
 * Description: You are given a list of 10 file URLs (strings only, no network call needed).
 * Create a program that:
 *  Starts one thread per file using Runnable
 *  Each thread prints its own name which “file” it's downloading % progress (0–100) simulated
 * Main thread should print a message: “All downloads started, main thread is free.”
 */
public class ParallelFileDownloader {
    public static void main(String[] args) {
        String[] files = new String[]{"file1", "file2", "file3", "file4", "file5"};
        createUsingExecutorService(files);

    }

    static void createUsingExecutorService(String[] files) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (String file : files) {
                executorService.submit(new FileDownloader(file));
            }
            executorService.shutdown();
        }
    }

    static void createDownloadThreads(String[] files) {
        for (int i = 0; i < files.length; i++) {
            var file = files[i];
            Thread downloader = new Thread(new FileDownloader(file));
            downloader.setName("Downloader-" + (i + 1));
            downloader.start();
        }
        System.out.println("main is free...");
    }
}

class FileDownloader implements Runnable {

    private final String file;

    FileDownloader(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName()
                + " started downloading file: " + file);
        try {
            for (int progress = 0; progress < 100; progress += 5) {
                System.out.println("Thread: " + Thread.currentThread().getName()
                        + " started downloading file: " + file + " progress: " + progress);
                Thread.sleep(400);
            }
            System.out.println("Thread: " + Thread.currentThread().getName()
                    + " completed downloading file: " + file);
        } catch (InterruptedException e) {
            System.err.println("File download interrupted");
            Thread.currentThread().interrupt();
        }
    }
}