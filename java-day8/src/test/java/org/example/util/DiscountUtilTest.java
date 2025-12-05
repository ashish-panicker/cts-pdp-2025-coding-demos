package org.example.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscountUtilTest {

    private final DiscountUtil util = new DiscountUtil();

    // Test method naming conventions
    // snake_case:              methoUnderTest_condition_expectedOutcome
    // bdd style:               givenCondition_whenAction_thenExpectedResult
    // should based naming:     shouldExpectBehaviour_whenCondition

    // @Test ensures that this is a test method
    @Test
    @DisplayName("20 Percent Discount")
    void calculateDiscount_amountAbove5000_return20PercentDiscount() {
        var discount = util.calculateDiscount(6000);
        assertEquals(1200, discount, 0.001);
    }

    @Test
    @DisplayName("10 Percent Discount")
    void calculateDiscount_amountAbove2000_return10PercentDiscount() {
        var discount = util.calculateDiscount(3000);
        assertEquals(300, discount, 0.001);
    }

    @Test
    @DisplayName("No Discount")
    void calculateDiscount_amountBelow2000_return0PercentDiscount() {
        var discount = util.calculateDiscount(1000);
        assertEquals(0, discount, 0.001);
    }

    @ParameterizedTest
    @CsvSource({"6000,1200", "3000,300", "1000,0"})
    void calculateDiscount_withVariousAmounts_returnCorrectDiscount(double amount, double expected) {
        var discount = util.calculateDiscount(amount);
        assertEquals(expected, discount, 0.001);
    }

    @RepeatedTest(10)
    void calculateDiscount_randomInputs_returnCorrectDiscount() {
        double amount = Math.random() * 7000; // 0-7000
        var discount = util.calculateDiscount(amount);
        if (amount > 5000) {
            assertEquals(amount * .20, discount, 0.001);
        } else if (amount > 2000) {
            assertEquals(amount * .10, discount, 0.001);
        } else {
            assertEquals(0, discount, 0.001);
        }
    }


}