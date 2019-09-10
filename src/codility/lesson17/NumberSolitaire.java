package codility.lesson17;

import helpers.TestHelper;

import java.util.Arrays;

public class NumberSolitaire {
    public static void main(String[] args) {
        int[] A =
//            {1, -2, 0, 9, -1, -2}
            {-2, 5, 1}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            int[] dp = new int[A.length];
            Arrays.fill(dp, Integer.MIN_VALUE);
            dp[0] = A[0];

            for (int i = 0; i < A.length; i++) {
                for (int dice = 1; dice <= 6; dice++) {
                    int next = i + dice;
                    if (next < A.length) {
                        dp[next] = Math.max(dp[next], dp[i] + A[next]);
                    }    
                }
            }
//            TestHelper.printArray(dp);

            return dp[A.length - 1];
        }
    }
}