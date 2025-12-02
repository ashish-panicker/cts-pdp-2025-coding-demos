package com.example.threading;

public class CpuLoader {

    public static long fib(long n) {
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    public static boolean isPrime(long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        }
        return true;
    }
}
