package com.test.q3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DigitSumCalculatorTest {

    @Test
    public void testSumOfDigitsBasic() {
        assertEquals(23, DigitSumCalculator.sumOfDigits("1234445"));
    }

    @Test
    public void testDigitalRootBasic() {
        assertEquals(5, DigitSumCalculator.digitalRoot("1234445"));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(7, DigitSumCalculator.sumOfDigits("7"));
        assertEquals(7, DigitSumCalculator.digitalRoot("7"));
    }

    @Test
    public void testLargeInput() {
        String input = "9999999999"; // 10 nines
        assertEquals(90, DigitSumCalculator.sumOfDigits(input));
        assertEquals(9, DigitSumCalculator.digitalRoot(input)); // 9+0=9
    }

    @Test
    public void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> DigitSumCalculator.sumOfDigits(""));
        assertThrows(IllegalArgumentException.class, () -> DigitSumCalculator.sumOfDigits(null));
    }
}
