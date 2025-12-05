package org.example.util;

public class DiscountUtil {

    public double calculateDiscount(double total) {
        if (total > 5000) return total * .20;
        if (total > 2000) return total * .10;
        return 0;
    }
}
