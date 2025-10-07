package com.test.q1;


public class TimeInWords {

    private static final String[] WORDS = {
        "", "one", "two", "three", "four", "five", "six",
        "seven", "eight", "nine", "ten", "eleven", "twelve",
        "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
        "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two",
        "twenty-three", "twenty-four", "twenty-five", "twenty-six",
        "twenty-seven", "twenty-eight", "twenty-nine"
    };

    /**
     * Converts time given by hour H (1..12) and minute M (0..59) to words.
     * Example: H=5, M=47 -> "Thirteen minutes to six"
     *
     * @throws IllegalArgumentException for invalid H or M
     */
    public static String timeToWords(int H, int M) {
        if (H < 1 || H > 12) {
            throw new IllegalArgumentException("Hour H must be between 1 and 12 inclusive.");
        }
        if (M < 0 || M > 59) {
            throw new IllegalArgumentException("Minute M must be between 0 and 59 inclusive.");
        }

        String result;
        if (M == 0) {
            result = WORDS[H] + " o'clock";
        } else if (M == 15) {
            result = "quarter past " + WORDS[H];
        } else if (M == 30) {
            result = "half past " + WORDS[H];
        } else if (M == 45) {
            int nextHour = (H % 12) + 1;
            result = "quarter to " + WORDS[nextHour];
        } else if (M < 30) {
            String minuteWord = (M == 1) ? " minute" : " minutes";
            result = capitalizeFirst(numberToWords(M) + minuteWord + " past " + WORDS[H]);
            return result;
        } else { // M > 30 and not 45
            int minutesTo = 60 - M;
            int nextHour = (H % 12) + 1;
            String minuteWord = (minutesTo == 1) ? " minute" : " minutes";
            result = numberToWords(minutesTo) + minuteWord + " to " + WORDS[nextHour];
        }

        // Ensure leading capitalization per sample output (first letter uppercase)
        return capitalizeFirst(result);
    }

    private static String numberToWords(int num) {
        if (num >= 1 && num <= 29) {
            return WORDS[num];
        }
        throw new IllegalArgumentException("This helper only supports 1..29: got " + num);
    }

    private static String capitalizeFirst(String s) {
        if (s == null || s.isEmpty()) return s;
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    // optional simple manual test
    public static void main(String[] args) {
        System.out.println(timeToWords(5, 0));   // Five o'clock
        System.out.println(timeToWords(5, 1));   // One minute past five
        System.out.println(timeToWords(5, 10));  // Ten minutes past five
        System.out.println(timeToWords(5, 30));  // Half past five
        System.out.println(timeToWords(5, 40));  // Twenty minutes to six
        System.out.println(timeToWords(5, 45));  // Quarter to six
        System.out.println(timeToWords(5, 47));  // Thirteen minutes to six
        System.out.println(timeToWords(5, 28));  // Twenty-eight minutes past five
    }
}
