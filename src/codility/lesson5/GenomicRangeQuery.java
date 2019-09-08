package codility.lesson5;

import helpers.TestHelper;

import java.util.Arrays;

public class GenomicRangeQuery {
    public static void main(String[] args) {
        String S =
//            "CAGCCTA"
//            "A"
            "TTG"
            ;
        int [] P =
//            {2, 5, 0}
//            {0}
            {0}
            ;
        int [] Q =
//            {4, 5, 6}
//            {0}
            {2}
            ;
        Object solution = new Solution().solution(S, P, Q);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int[] solution(String S, int[] P, int[] Q) {
            int[][] prefixCounts = new int[3][S.length() + 1];
            int[] minFactors = new int[P.length];

            for (int i = 0; i < S.length(); i++) {
                char c = S.charAt(i);
                int A = 0, C = 0, G = 0;

                if (c == 'A') {
                    A = 1;
                } else if (c == 'C') {
                    C = 1;
                } else if (c == 'G') {
                    G = 1;
                }
                prefixCounts[0][i + 1] = prefixCounts[0][i] + A;
                prefixCounts[1][i + 1] = prefixCounts[1][i] + C;
                prefixCounts[2][i + 1] = prefixCounts[2][i] + G;
            }

            for (int i = 0; i < P.length; i++) {
                int pCommand = P[i];
                int qCommand = Q[i];

                if (prefixCounts[0][qCommand + 1] - prefixCounts[0][pCommand] > 0) {
                    minFactors[i] = 1;
                } else if (prefixCounts[1][qCommand + 1] - prefixCounts[1][pCommand] > 0) {
                    minFactors[i] = 2;
                } else if (prefixCounts[2][qCommand + 1] - prefixCounts[2][pCommand] > 0) {
                    minFactors[i] = 3;
                } else {
                    minFactors[i] = 4;
                }
            }

            return minFactors;
        }
    }
}