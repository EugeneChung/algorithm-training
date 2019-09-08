package codility;

import helpers.TestHelper;

public class MaxProfit {
    public static void main(String[] args) {
        int [] P =
            {23171, 21011, 21123, 21366, 21013, 21367}
//            {-3, 1, 2, -2, 5, 6}
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
            if (A.length < 2) return 0;

            int minPrice = A[0];
            int maxEnd = 0;
            int anwser = 0;

            for (int i = 1; i < A.length; i++) {
                maxEnd = Math.max(0, A[i] - minPrice);
                minPrice = Math.min(minPrice, A[i]);
                anwser = Math.max(anwser, maxEnd);
            }
            return anwser;
        }
    }
}