package com.test.q2;

public class RemoveDuplicates {

    /**
     * Removes duplicates within each row of a 2D integer array.
     * For each duplicate found per row, replaces with 0.
     *
     * @param matrix 2D integer array
     * @return modified 2D array with duplicates replaced by 0
     */
    public static int[][] removeDuplicates(int[][] matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("Matrix cannot be null");
        }

        for (int i = 0; i < matrix.length; i++) {
            int[] row = matrix[i];
            for (int j = 0; j < row.length; j++) {
                for (int k = 0; k < j; k++) {
                    if (row[k] == row[j] && row[j] != 0) {
                        row[j] = 0;
                        break;
                    }
                }
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.print("{ ");
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("}");
        }
    }

    // Simple manual test
    public static void main(String[] args) {
        int[][] input = {
            {1, 3, 1, 2, 3, 4, 4, 3, 5},
            {1, 1, 1, 1, 1, 1, 1}
        };

        int[][] result = removeDuplicates(input);
        printMatrix(result);
    }
}