package com.example.StudentScoreApi.util;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsUtilTest {

    @Test
    void testMean() {
        assertEquals(3.0, StatisticsUtil.mean(List.of(1,2,3,4,5)));
    }

    @Test
    void testMedianOddEven() {
        assertEquals(3.0, StatisticsUtil.median(List.of(1,2,3,4,5)));
        assertEquals(2.5, StatisticsUtil.median(List.of(1,2,3,4)));
    }

    @Test
    void testMode() {
        assertEquals(2, StatisticsUtil.mode(List.of(1,2,2,3)));
        assertNull(StatisticsUtil.mode(List.of(1,2,3))); // No mode
    }
}
