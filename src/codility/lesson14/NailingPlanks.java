package codility.lesson14;

import helpers.TestHelper;

import java.util.Arrays;

public class NailingPlanks {
    public static void main(String[] args) {
        int[] A =
//            {1, 4, 5, 8}
//            {1}
//            {1}
//            {1}
//            {1, 4, 5, 8}
            {1, 4, 5, 8}
            ;
        int[] B =
//            {4, 5, 9, 10}
//            {100}
//            {100}
//            {100}
//            {4, 5, 9, 10}
            {1, 4, 5, 8}
            ;
        int[] C =
//            {4, 6, 7, 10, 2} // 4
//            {4} //1
//            {4, 5, 6, 7, 8, 9} // 1
//            {4, 4, 4, 4, 4, 4} // 1
//            {8, 5, 12} // -1
            {1, 4, 5, 8} // 4
            ;
        Object solution = new Solution().solution(A, B, C);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        private int maxInArray(int... array) {
            int max = array[0];
            for (int i = 1; i < array.length; i++) {
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }

        public int solution(int[] A, int[] B, int[] C) {
            int maxInB = maxInArray(B);
            int maxInC = maxInArray(C);
            int maxPositions = Math.max(maxInB, maxInC) + 1;
            int[] nailCounts = new int[maxPositions];
            int low = 1;
            int high = C.length;

            int minNailCount = -1;
            while (low <= high) {
                int mid = (low + high) / 2;

                for (int i = 0; i < mid; i++) {
                    nailCounts[C[i]] = 1;
                }
                for (int i = 1; i < nailCounts.length; i++) {
                    nailCounts[i] += nailCounts[i - 1];
                }

                boolean isAllPlanksNailed = true;
                for (int i = 0; i < A.length; i++) {
                    if (nailCounts[B[i]] == nailCounts[A[i] - 1]) {
                        isAllPlanksNailed = false;
                        break;
                    }
                }

                if (isAllPlanksNailed) {
                    minNailCount = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
                Arrays.fill(nailCounts, 0);
            }
            return minNailCount;
        }
    }
}