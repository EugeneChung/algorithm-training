package codility;

import helpers.TestHelper;

import java.util.Arrays;

public class GenomicRangeQuery {
    public static void main(String[] args) {
        String S =
            //"CAGCCTA"
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
        private int getImpactFactor(char type) {
            switch (type) {
                case 'A': return 1;
                case 'C': return 2;
                case 'G': return 3;
                case 'T': return 4;
                default: return 0;
            }
        }

        public int[] solution(String S, int[] P, int[] Q) {
            int[] minFactors = new int[P.length];

            for (int i = 0; i < P.length; i++) {
                int pCommand = P[i];
                int qCommand = Q[i];

                char[] subSequence = S.substring(pCommand, qCommand + 1).toCharArray();
                Arrays.sort(subSequence);
                minFactors[i] = getImpactFactor(subSequence[0]);
            }

            return minFactors;
        } 
    }
}