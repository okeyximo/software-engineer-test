package com.test.q3;


public class DigitSumCalculator {

    /**
     * Recursively calculates the sum of digits in a string of digits.
     *
     * @param numberString the input string containing digits
     * @return the sum of digits
     */
    public static int sumOfDigits(String numberString) {
        if (numberString == null || numberString.isEmpty()) {
            throw new IllegalArgumentException("Input string must not be null or empty");
        }

        // Base case: single character
        if (numberString.length() == 1) {
            return Character.getNumericValue(numberString.charAt(0));
        }

        // Recursive case: add first digit + sum of remaining
        int firstDigit = Character.getNumericValue(numberString.charAt(0));
        return firstDigit + sumOfDigits(numberString.substring(1));
    }

    /**
     * Recursively computes the digital root of a number string.
     * The digital root is obtained by repeatedly summing digits until a single digit remains.
     *
     * @param numberString the input string containing digits
     * @return the single-digit digital root
     */
    public static int digitalRoot(String numberString) {
        int sum = sumOfDigits(numberString);
        if (sum < 10) {
            return sum;
        }
        return digitalRoot(String.valueOf(sum));
    }

    public static void main(String[] args) {
        String input = "1234445123444512344451234445123444512344451234445";
        int sum = sumOfDigits(input);
        int root = digitalRoot(input);

        System.out.println("Sum of digits: " + sum);
        System.out.println("Digital root: " + root);
    }
}