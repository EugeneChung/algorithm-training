package codility.lesson17;

import helpers.TestHelper;

public class MinAbsSum {
    public static void main(String[] args) {
        int[] A =
//            {}
//            {-100, 100}
//            {4, 3, 2, 1}
//            {1}
//            {1, 10, 100}
//            {100, 10, 1}
//            {1, 5, 2, -2}
            {3, 3, 3, 4, 5}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 0) return 0;

            int[] dp = new int[A.length];
            //Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = Math.abs(A[0]);

            for (int i = 1; i < A.length; i++) {
                int factor = A[i];
                if (i % 2 == 1) {
                    factor = -factor;
                }
                int absResultFromMinus = Math.abs(-dp[i - 1] + factor);
                int absResultFromPlus = Math.abs(dp[i - 1] + factor);

                if (i == A.length - 1) {
                    dp[i] = Math.min(absResultFromMinus, absResultFromPlus);
                } else {
                    int nextFactor = A[i + 1];
                    if (i % 2 == 1) {
                        nextFactor = -nextFactor;
                    }
                    int nextAbsResultFromMinus = Math.abs(absResultFromMinus + nextFactor);
                    int nextAbsResultFromPlus = Math.abs(absResultFromPlus + nextFactor);
                    if (nextAbsResultFromMinus < nextAbsResultFromPlus) {
                        dp[i] = absResultFromMinus;
                    } else {
                        dp[i] = absResultFromPlus;
                    }
                }
            }
            TestHelper.printObject(dp);

            return dp[A.length - 1];
        }
    }
}