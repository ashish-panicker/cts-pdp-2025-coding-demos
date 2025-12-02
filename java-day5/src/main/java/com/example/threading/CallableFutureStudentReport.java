package com.example.threading;

import java.util.concurrent.*;

public class CallableFutureStudentReport {
    public static void main(String[] args) throws Exception {

        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

            Callable<Integer> attendanceTask = () -> {
                System.out.println("Calculating Attendance...");
                Thread.sleep(800); // simulating external call
                return 92; // in %
            };

            Callable<Double> assessmentTask = () -> {
                System.out.println("Collecting Assessment Marks...");
                Thread.sleep(1200);
                return 88.5; // average marks
            };

            Callable<Integer> extraFactorTask = () -> {
                System.out.println("Evaluating Extra-Curricular Factor...");
                Thread.sleep(1000);
                return 7; // out of 10
            };

            Future<Integer> attendanceFuture = executor.submit(attendanceTask);
            Future<Double> assessmentFuture = executor.submit(assessmentTask);
            Future<Integer> extraFuture = executor.submit(extraFactorTask);

            System.out.println("Main thread doing other work...\n");

            try {
                int attendance = attendanceFuture.get();
                double assessment = assessmentFuture.get();
                int extra = extraFuture.get();

                double finalScore = calculateFinalScore(attendance, assessment, extra);

                System.out.println("\n===== FINAL PROGRESS REPORT =====");
                System.out.println("Attendance (%)         : " + attendance);
                System.out.println("Assessment Avg Score   : " + assessment);
                System.out.println("Extra Factor (10)      : " + extra);
                System.out.println("-----------------------------------");
                System.out.println("Final Progress Score   : " + finalScore);

            } catch (ExecutionException e) {
                System.err.println("A task failed: " + e.getCause());
            }

            executor.shutdown();
        }
    }

    private static double calculateFinalScore(int attendance, double assessment, int extra) {
        return attendance * 0.3 + assessment * 0.5 + extra * 2;
    }
}
