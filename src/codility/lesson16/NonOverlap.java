package codility.lesson16;

import helpers.TestHelper;

public class NonOverlap {
    public static void main(String[] args) {
        int[] A =
            {1, 3, 7, 9, 9}
//            {1, 1, 1}
//            {1, 1, 1, 2}
//            {1, 2, 3, 4}
            ;
        int[] B =
            {5, 6, 8, 9, 10}
//            {1, 1, 1}
//            {1, 1, 1, 2}
//            {1, 2, 3, 4}
            ;
        Object solution = new Solution().solution(A, B);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A, int[] B) {
            if (A.length == 0) return 0;

            int nonOverlapSetCount = 1;
            int currentSegmentEnd = B[0];
            for (int i = 0; i < A.length - 1; i++) {
                if (currentSegmentEnd < A[i + 1]) { // non-overlap
                    currentSegmentEnd = B[i + 1];
                    nonOverlapSetCount++;
                }
            }
            return nonOverlapSetCount;
        }
    }
}