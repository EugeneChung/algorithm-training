package codility.lesson9;

import helpers.TestHelper;

public class MaxDoubleSliceSum {
    public static void main(String[] args) {
        int [] A =
            {3, 2, 6, -1, 4, 5, -1, 2}
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 3) return 0;

            int maxEndingLeft = 0;
            int maxSoFarLeft = 0;
            int maxEndingRight = 0;
            int maxSoFarRight = 0;

            for (int x = 0; x < A.length - 2; x++) {
                for (int y = x + 1; y < A.length - 1; y++) {
                    for (int z = y + 1; z < A.length; z++) {
                        maxEndingRight = Math.max(A[x], A[x] + maxEndingRight);
                        //maxSoFar = Math.max(maxSoFar, maxEnding);
                    }
                }
            }
            return 0;
        }
    }
}