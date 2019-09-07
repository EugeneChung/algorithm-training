package codility;

import helpers.TestHelper;

import java.util.Arrays;

public class Distinct {
    public static void main(String[] args) {
        int [] P =
//            {-3, 1, 2, -2, 5, 6}
//            {2, 1, 1, 2, 3, 1}
//            {0}
//            {1, 1, 1}
            {1, -1, 1}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 0) return 0;
            if (A.length == 1) return 1;

            Arrays.sort(A);

            int distict = 1;
            int curr = A[0];

            for (int i = 1; i < A.length; i++) {
                if (curr != A[i]) {
                    curr = A[i];
                    distict++;
                }
            }

            return distict;
        }
    }
}