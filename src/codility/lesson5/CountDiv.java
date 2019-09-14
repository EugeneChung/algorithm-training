package codility.lesson5;

import helpers.TestHelper;

public class CountDiv {
    public static void main(String[] args) {
//        int A = 0, B = 0, K = 2; // 1
        int A = 3, B = 3, K = 2; // 0
//        int A = 6, B = 11, K = 2; // 3
        Object solution = new Solution().solution(A, B, K);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int A, int B, int K) {
            int result = B / K + 1;
            if (A != 0) {
                result -= ((A - 1) / K + 1);
            }
            return result;
        }
    }
}