package codility.lesson6;

import helpers.TestHelper;

import java.util.Arrays;

public class MaxProductOfThree {
    public static void main(String[] args) {
        int [] P =
            {-3, 1, 2, -2, 5, 6}
//            {0}
//            {0}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            Arrays.sort(A);
            int maxIndex = A.length - 1;
            return Math.max(A[maxIndex] * A[maxIndex - 1] * A[maxIndex - 2], A[maxIndex] * A[0] * A[1]);
        }
    }
}