package com.test.q1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TimeInWordsTest {

    @Test
    public void testOClock() {
        assertEquals("Five o'clock", TimeInWords.timeToWords(5, 0));
    }

    @Test
    public void testOneMinutePast() {
        assertEquals("One minute past five", TimeInWords.timeToWords(5, 1));
    }

    @Test
    public void testTenMinutesPast() {
        assertEquals("Ten minutes past five", TimeInWords.timeToWords(5, 10));
    }

    @Test
    public void testHalfPast() {
        assertEquals("Half past five", TimeInWords.timeToWords(5, 30));
    }

    @Test
    public void testTwentyTo() {
        assertEquals("Twenty minutes to six", TimeInWords.timeToWords(5, 40));
    }

    @Test
    public void testQuarterTo() {
        assertEquals("Quarter to six", TimeInWords.timeToWords(5, 45));
    }

    @Test
    public void testThirteenTo() {
        assertEquals("Thirteen minutes to six", TimeInWords.timeToWords(5, 47));
    }

    @Test
    public void testTwentyEightPast() {
        assertEquals("Twenty-eight minutes past five", TimeInWords.timeToWords(5, 28));
    }

    // Edge cases
    @Test
    public void testMinute59() {
        assertEquals("One minute to two", TimeInWords.timeToWords(1, 59));
    }

    @Test
    public void testInvalidHour() {
        assertThrows(IllegalArgumentException.class, () -> TimeInWords.timeToWords(13, 0));
    }

    @Test
    public void testInvalidMinute() {
        assertThrows(IllegalArgumentException.class, () -> TimeInWords.timeToWords(12, 60));
    }

    @Test
    public void testLowerBoundHour() {
        assertEquals("One o'clock", TimeInWords.timeToWords(1, 0));
    }
}
