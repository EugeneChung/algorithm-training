package codility;

import helpers.TestHelper;

public class MaxSliceSum {
    public static void main(String[] args) {
        int [] P =
            //{3, 2, -6, 4, 0}
//            {-3, 3, -3, -1, -1}
            {-1, -1, -1, -1, -1, -1}
//            {2, 1, 1, 2, 3, 1}
//            {1, 1, }
//            {1, 1, 1}
//            {3, 2, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            int maxEnding = A[0];
            int maxSoFar = A[0];

            for (int i = 1; i < A.length; i++) {
                maxEnding = Math.max(A[i], A[i] + maxEnding);
                maxSoFar = Math.max(maxSoFar, maxEnding);
            }
            return maxSoFar;
        }
    }
}