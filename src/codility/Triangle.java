package codility;

import helpers.TestHelper;

import java.util.Arrays;

public class Triangle {
    public static void main(String[] args) {
        int [] P =
//            {-3, 1, 2, -2, 5, 6}
//            {2, 1, 1, 2, 3, 1}
//            {0}
            {5, 10, 8}
//            {1, 1, 1}
//            {1, -1, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length < 3) return 0;

            Arrays.sort(A);

            for (int i = 0; i < A.length - 2; i++) {
                long val1 = A[i];
                long val2 = A[i + 1];
                long val3 = A[i + 2];
                if (val1 + val2 > val3) {
                    return 1;
                }
            }
            return 0;
        }
    }
}