package com.example.threading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CFStudentProgressDemo {
    public static void main(String[] args) {

        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {

            CompletableFuture<Integer> attendanceCF =
                    CompletableFuture.supplyAsync(() -> {
                        sleep(800);
                        System.out.println("Attendance calculated");
                        return 92;
                    }, executor).exceptionally(ex -> {
                        System.out.println("Attendance task failed: " + ex.getMessage());
                        return 0;
                    });

            CompletableFuture<Double> assessmentCF =
                    CompletableFuture.supplyAsync(() -> {
                        sleep(1200);
                        System.out.println("Assessment marks collected");
                        return 88.5;
                    }, executor).exceptionally(ex -> {
                        System.out.println("Assessment task failed: " + ex.getMessage());
                        return 0.0;
                    });

            CompletableFuture<Integer> extraCF =
                    CompletableFuture.supplyAsync(() -> {
                        sleep(1000);
                        System.out.println("Extra factor evaluated");
                        return 7;
                    }, executor).exceptionally(ex -> {
                        System.out.println("Extra task failed: " + ex.getMessage());
                        return 0;
                    });

            CompletableFuture<Void> allTasks =
                    CompletableFuture.allOf(attendanceCF, assessmentCF, extraCF);

            CompletableFuture<StudentReport> finalReportCF = allTasks.thenApply(v -> {

                int attendance = attendanceCF.join();
                double assessment = assessmentCF.join();
                int extra = extraCF.join();

                double finalScore = attendance * 0.3 + assessment * 0.5 + extra * 2;

                return new StudentReport(attendance, assessment, extra, finalScore);
            });

            finalReportCF.thenAccept(report -> {
                System.out.println("\n===== FINAL PROGRESS REPORT =====");
                System.out.println(report);
            }).join();

            executor.shutdown();
        }
    }

    private static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (Exception e) {}
    }
}

class StudentReport {
    int attendance;
    double assessment;
    int extra;
    double finalScore;

    public StudentReport(int attendance, double assessment, int extra, double finalScore) {
        this.attendance = attendance;
        this.assessment = assessment;
        this.extra = extra;
        this.finalScore = finalScore;
    }

    public String toString() {
        return "Attendance (%)      : " + attendance +
                "\nAssessment Avg      : " + assessment +
                "\nExtra Factor (10)   : " + extra +
                "\n--------------------------------" +
                "\nFinal Progress Score: " + finalScore;
    }
}
