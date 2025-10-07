package com.test.q2;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RemoveDuplicatesTest {

    @Test
    public void testBasicCase() {
        int[][] input = {
            {1, 3, 1, 2, 3, 4, 4, 3, 5},
            {1, 1, 1, 1, 1, 1, 1}
        };
        int[][] expected = {
            {1, 3, 0, 2, 0, 4, 0, 0, 5},
            {1, 0, 0, 0, 0, 0, 0}
        };

        int[][] result = RemoveDuplicates.removeDuplicates(input);
        assertArrayEquals(expected[0], result[0]);
        assertArrayEquals(expected[1], result[1]);
    }

    @Test
    public void testNoDuplicates() {
        int[][] input = {{1, 2, 3, 4}};
        int[][] expected = {{1, 2, 3, 4}};
        assertArrayEquals(expected[0], RemoveDuplicates.removeDuplicates(input)[0]);
    }

    @Test
    public void testAllSameElements() {
        int[][] input = {{5, 5, 5, 5}};
        int[][] expected = {{5, 0, 0, 0}};
        assertArrayEquals(expected[0], RemoveDuplicates.removeDuplicates(input)[0]);
    }

    @Test
    public void testEmptyMatrix() {
        int[][] input = new int[0][];
        int[][] result = RemoveDuplicates.removeDuplicates(input);
        assertEquals(0, result.length);
    }

    @Test
    public void testNullMatrixThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> RemoveDuplicates.removeDuplicates(null));
    }

    @Test
    public void testSingleRowSingleElement() {
        int[][] input = {{42}};
        int[][] expected = {{42}};
        assertArrayEquals(expected[0], RemoveDuplicates.removeDuplicates(input)[0]);
    }
}
