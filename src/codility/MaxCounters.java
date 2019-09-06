package codility;

import helpers.TestHelper;

import java.util.Arrays;

public class MaxCounters {
    public static void main(String[] args) {
        int[] A =
            //{ 3, 4, 4, 6, 1, 4, 4 }
            { 3, 4, 4, 6, 1, 4, 4, 6 }
            //{ 3, 3, 3, 3, 3 }
            //{ 1, 2, 3, 5, 4, 5 }
            //{1, 3, 1, 3, 2, 1, 3}
            //{1, 3, 2}
            ;
        int N =
            //5
            5
            //2
            //5
            //3
            //3
            ;
        Object solution = new Solution().solution(N, A);
        TestHelper.printSolution(solution);
    }

    static class Solution {
        public int[] solution(int N, int[] A) {
            int[] counters = new int[N];
            final int MAX_COUNTER = N + 1;
            int currentMax = 0;
            boolean maxCounterOn = false;

            for (int opValue : A) {
                if (opValue == MAX_COUNTER) {
                    if (!maxCounterOn) {
                        Arrays.fill(counters, currentMax);
                        maxCounterOn = true;
                    }
                } else {
                    maxCounterOn = false;
                    counters[opValue - 1] += 1;
                    currentMax = Math.max(counters[opValue - 1], currentMax);
                }
            }

            return counters;
        }
    }
}