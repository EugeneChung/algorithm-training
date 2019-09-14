package codility.lesson5;

import helpers.TestHelper;

public class PassingCars {
    public static void main(String[] args) {
        int[] A =
            { 0, 1, 0, 1, 1 }
//            { 1, 0, 1 } //1
//            { 0, 0, 0 }//0
//            { 1, 1, 1 } //0
//            { 1 } // 0
//            { 0 } // 0
//            { 1, 0, 0 } // 0
//            { 0, 0, 1 } // 2
            ;
        Object solution = new Solution().solution(A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 1) return 0;
            int[] suffixSum = new int[A.length + 1];

            for (int i = A.length - 1; i >= 0; i--) {
                suffixSum[i] = suffixSum[i + 1] + A[i];
            }
//            TestHelper.printObject(suffixSum);

            int pairs = 0;
            for (int i = 0; i < A.length; i++) {
                int carDirection = A[i];
                if (carDirection == 0) {
                    pairs += suffixSum[i];
                    if (pairs > 1000000000) {
                        return -1;
                    }
                }
            }
            return pairs;
        }
    }
}