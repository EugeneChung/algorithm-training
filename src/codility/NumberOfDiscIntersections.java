package codility;

import helpers.TestHelper;

import java.util.Arrays;

public class NumberOfDiscIntersections {
    public static void main(String[] args) {
        int [] P =
//            {1, 5, 2, 1, 4, 0}
//            {0}
//            {0, 1}
//            {5, 10, 8}
            {1, 1, 1}
//            {1, -1, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 2) return 0;

            long[] L = new long[A.length];
            for (int i = 0; i < A.length; i++) {
                long radius = A[i];
                L[i] = i - radius;
            }
            Arrays.sort(L);
            //TestHelper.printArray(Arrays.toString(L));

            long answer = 0;
            for (int i = 0; i < A.length - 1; i++) {
                long radius = A[i];
                long R = i + radius;
                long count = Arrays.binarySearch(L, R + 1);
                if (count < 0) {
                    count = -count - 1;
                }
                //TestHelper.log(count);
                answer += count - 1 - i;
            }
            if (answer > 10000000) {
                return -1;
            }
            return (int)answer;
        }
    }
}