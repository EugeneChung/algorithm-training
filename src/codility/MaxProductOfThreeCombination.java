package codility;

import helpers.TestHelper;

public class MaxProductOfThreeCombination {
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
            int[] selected = new int[3];
            return doCombination(A, 3, selected, 0, 0, Integer.MIN_VALUE);
        }

        public int doCombination(int[] A, int r, int[] selected, int selectedPos, int targetIndex, int currentAnswer) {
            if (r == 0) {
                int product = 1;
                for (int i = 0; i < selectedPos; i++) {
                    product *= A[selected[i]];
                }
                return Math.max(product, currentAnswer);
            } else if (targetIndex == A.length) {
                return currentAnswer;
            } else {
                selected[selectedPos] = targetIndex;
                int product1 = doCombination(A, r - 1, selected, selectedPos + 1, targetIndex + 1, currentAnswer);
                return doCombination(A, r, selected, selectedPos, targetIndex + 1, product1);
            }
        }
    }
}