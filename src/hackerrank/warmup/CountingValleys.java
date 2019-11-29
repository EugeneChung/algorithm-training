package hackerrank.warmup;

import helpers.TestHelper;

public class CountingValleys {
    public static void main(String[] args) {
//        String s = "UDDDUDUU"; int n = 8; // 1
//        String s = "DDUUUUDD"; int n = 8; // 1
        String s = "DDUUDDUU"; int n = 8; // 2

        TestHelper.printSolution(
            countingValleys(n, s)
        );
    }

    static int countingValleys(int n, String s) {
        int depth = 0;
        int valley = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'U') {
                if (depth == -1) valley++;
                depth++;
            } else {
                depth--;
            }
        }

        return valley;
    }
}
