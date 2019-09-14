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
            int maxEndingRight = 0;

            int[] maxEndingFromLeftList = new int[A.length];
            int[] maxEndingFromRightList = new int[A.length];

            for (int i = 1; i < A.length - 1; i++) {
                maxEndingLeft = Math.max(0, maxEndingLeft + A[i]);
                maxEndingFromLeftList[i] = maxEndingLeft;
            }

            for (int i = A.length - 2; i > 0; i--) {
                maxEndingRight = Math.max(0, maxEndingRight + A[i]);
                maxEndingFromRightList[i] = maxEndingRight;
            }
            TestHelper.printArray(maxEndingFromLeftList);
            TestHelper.printArray(maxEndingFromRightList);

            int answer = Integer.MIN_VALUE;
            for (int y = 1; y < A.length - 1; y++) {
                int leftSum = maxEndingFromLeftList[y - 1];
                int rightSum = maxEndingFromRightList[y + 1];
                answer = Math.max(answer, leftSum + rightSum);
            }
            return answer;
        }
    }
}