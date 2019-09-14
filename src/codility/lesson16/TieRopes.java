package codility.lesson16;

import helpers.TestHelper;

public class TieRopes {
    public static void main(String[] args) {
        int K =
//            4
            4
            ;
        int[] A =
//            {1, 2, 3, 4, 1, 1, 3}
            {1}
            ;
        Object solution = new Solution().solution(K, A);
        TestHelper.printSolution(solution);
    }

    //https://app.codility.com/demo/results/trainingQKU7U3-6X3/
    static class Solution {
        public int solution(int K, int[] A) {
            int tiedRopesCount = 0;
            int currentTiedRopeLen = 0;
            for (int i = 0; i < A.length; i++) {
                currentTiedRopeLen += A[i];
                if (currentTiedRopeLen >= K) {
                    tiedRopesCount++;
                    currentTiedRopeLen = 0;
                }
            }
            return tiedRopesCount;
        }
    }
}