package codility;

import helpers.TestHelper;

import java.util.*;

public class Dominator {
    public static void main(String[] args) {
        int [] P =
//            {3, 4, 3, 2, 3, -1, 3, 3}
//            {0}
//            {0, 1}
            {0, 0}
//            {5, 10, 8}
//            {1, 1, 1}
//            {1, -1, 1}
//            {0, 0, 1, 0, 0}
            ;
        Object solution = new Solution().solution(P);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int solution(int[] A) {
            if (A.length == 0) return -1;
            if (A.length == 1) return 0;

            final int half = A.length >> 1;

            int dominatorIndex = -1;
            int dominatorCount = 0;
            Map<Integer, Integer> occurrenceMap = new HashMap<>();

            for (int i = 0; i < A.length; i++) {
                int num = A[i];
                int count = occurrenceMap.compute(num, (k, v) -> (v == null) ? 1 : v + 1);
                if (dominatorCount < count) {
                    dominatorCount = count;
                    dominatorIndex = i;
                    if (dominatorCount > half) {
                        break;
                    }
                }
            }
            if (dominatorCount > half) return dominatorIndex;
            else return -1;
        }
    }
}