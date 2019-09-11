package codility.lesson1;

import helpers.TestHelper;

public class BinaryGap {
    public static void main(String[] args) {
        TestHelper.printSolution(new Solution().solution(
//            2147483647
//            9
//            529
//            20
//            15
//            157
            2
        ));
    }

    static class Solution {
        public int solution(int N) {
            if (N == 1) return 0;

            boolean startCount = false;
            int currentGap = 0;
            int longestGap = 0;
            int next = N;

            while (next > 0) {
                if (next % 2 == 1) {
                    startCount = true;
                    if (currentGap > 0) {
                        longestGap = Math.max(longestGap, currentGap);
                        currentGap = 0;
                    }
                } else {
                    if (startCount) currentGap++;
                }
                next = next >> 1;
            }

            return longestGap;
        }

    }
}